package cn.taroco.admin.registry;

import cn.taroco.admin.config.TarocoAdminServerProperties;
import cn.taroco.admin.converter.ServiceInstanceConverter;
import cn.taroco.admin.event.ClientApplicationStatusChangedEvent;
import cn.taroco.admin.model.Application;
import cn.taroco.admin.model.StatusInfo;
import cn.taroco.admin.registry.store.ApplicationStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * 服务监听缓存
 *
 * @author liuht
 * @date 2017/11/26 11:22
 */
public class ApplicationRegistry implements ApplicationEventPublisherAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationRegistry.class);

    private final ApplicationStore applicationStore;

    private final DiscoveryClient discoveryClient;

    private final ServiceInstanceConverter instanceConverter;

    private final TaskScheduler taskScheduler;

    private final TarocoAdminServerProperties serverProperties;

    private final RestTemplate restTemplate;

    private ApplicationEventPublisher eventPublisher;

    public ApplicationRegistry(ApplicationStore applicationStore, DiscoveryClient discoveryClient, ServiceInstanceConverter instanceConverter, TaskScheduler taskScheduler, TarocoAdminServerProperties serverProperties, RestTemplate restTemplate) {
        this.applicationStore = applicationStore;
        this.discoveryClient = discoveryClient;
        this.instanceConverter = instanceConverter;
        this.taskScheduler = taskScheduler;
        this.serverProperties = serverProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    /**
     * Application容器启动成功以后, 缓存所有服务
     */
    public void registryApps() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("starting registry app from discoveryClient...");
        }
        List<String> serviceIds = discoveryClient.getServices();
        for (String serviceId : serviceIds) {
            for (Application app : getApplicationsByName(serviceId)) {
                applicationStore.save(app);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("registry apps from discoveryClient: {}", applicationStore.findAll());
        }
    }

    /**
     * 根据服务名称获取 Application列表
     *
     * @param serviceId 服务名称
     * @return Collection<Application>
     */
    private Collection<Application> getApplicationsByName(String serviceId) {
        List<Application> apps = new ArrayList<>(10);
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceId);
        for (ServiceInstance instance : serviceInstances) {
            apps.add(instanceConverter.convert(instance));
        }
        return apps;
    }

    public void initInterval() {
        taskScheduler.scheduleAtFixedRate(() -> {
            // 刷新apps
            registryApps();
            Collection<Application> apps = applicationStore.findAll();
            // 循环刷新app 状态
            for (Application app : apps) {
                taskScheduler.schedule(new AppRefresh(app, restTemplate), new Date());
            }
        }, serverProperties.getMonitor().getStatusRefreshIntervalInMills());
    }

    public Collection<Application> getApplications() {
        return applicationStore.findAll();
    }

    private class AppRefresh implements Runnable {
        private final Application app;

        private final RestTemplate restTemplate;

        AppRefresh(Application app, RestTemplate restTemplate) {
            this.app = app;
            this.restTemplate = restTemplate;
        }

        @Override
        public void run() {
            final String healthUrl = app.getInstance().getHealthCheckUrl();
            if (StringUtils.isEmpty(healthUrl)) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn("Application: {}, has empty healthCheckUrl!", app.getInstance().getInstanceId());
                }
            } else {
                StatusInfo from = StatusInfo.valueOf(app.getInstance().getStatus());
                try {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Fetching Application Health '{}' for {}", healthUrl, app);
                    }
                    ResponseEntity<Map> response = restTemplate.exchange(healthUrl, HttpMethod.GET, null, Map.class);
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("'{}' responded with {}", healthUrl, response);
                    }

                    Map map = response.getBody();
                    if (!CollectionUtils.isEmpty(map) && map.containsKey(StatusInfo.STATUS_KEY)) {
                        StatusInfo to = StatusInfo.valueOf(String.valueOf(map.get(StatusInfo.STATUS_KEY)));
                        if (!from.getStatus().equals(to.getStatus())) {
                            // 状态变更
                            eventPublisher.publishEvent(new ClientApplicationStatusChangedEvent(app, from, to));
                            if (LOGGER.isDebugEnabled()) {
                                LOGGER.error("APP: {}, Status Changed from :{} to :{}", app.getInstance().getInstanceId() + "-" + app.getInstance().getHealthCheckUrl(),
                                        from.toString(), to.toString());
                            }
                        }
                    }
                } catch (Exception e) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error("APP: {}, health check failed", app.getInstance().getInstanceId() + "-" + app.getInstance().getHealthCheckUrl());
                    }
                    if (from.isUp()) {
                        // 只有当app 原来的状态是UP的时候,才发布事件
                        // 因为有可能app是本来就是down的,再发布事件就没有意义.
                        StatusInfo to = StatusInfo.ofDown();
                        eventPublisher.publishEvent(new ClientApplicationStatusChangedEvent(app, from, to));
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.error("APP: {}, Status Changed from :{} to :{}", app.getInstance().getInstanceId() + "-" + app.getInstance().getHealthCheckUrl(),
                                    from.toString(), to.toString());
                        }
                    }
                }
            }
        }
    }
}

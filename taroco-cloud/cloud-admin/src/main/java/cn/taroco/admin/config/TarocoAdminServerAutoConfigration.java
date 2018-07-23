package cn.taroco.admin.config;

import cn.taroco.admin.converter.DefaultServiceInstanceConverter;
import cn.taroco.admin.converter.ServiceInstanceConverter;
import cn.taroco.admin.event.ClientApplicationDeregisteredEvent;
import cn.taroco.admin.event.ClientApplicationRegisteredEvent;
import cn.taroco.admin.event.ClientApplicationStatusChangedEvent;
import cn.taroco.admin.event.RoutesOutdatedEvent;
import cn.taroco.admin.model.Application;
import cn.taroco.admin.model.StatusInfo;
import cn.taroco.admin.registry.ApplicationRegistry;
import cn.taroco.admin.registry.store.ApplicationStore;
import cn.taroco.admin.registry.store.SimpleApplicationStore;
import cn.taroco.admin.web.client.BasicAuthHttpHeaderProvider;
import cn.taroco.admin.web.client.HttpHeadersProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 服务治理配置
 *
 * @author liuht
 * @date 2017/11/26 20:58
 */
@Configuration
@EnableConfigurationProperties(TarocoAdminServerProperties.class)
@ConditionalOnProperty(prefix = "taroco.admin", value = "enabled", havingValue = "true", matchIfMissing = true)
public class TarocoAdminServerAutoConfigration {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Bean
    @ConditionalOnMissingBean(ApplicationStore.class)
    public ApplicationStore applicationStore() {
        return new SimpleApplicationStore();
    }

    @Bean("taskScheduler")
    public TaskScheduler taskScheduler(TarocoAdminServerProperties serverProperties) {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(serverProperties.getTask().getPoolSize());
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        scheduler.setThreadNamePrefix("taroco-admin-registrypool-");
        scheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return scheduler;
    }

    @Bean
    @ConditionalOnMissingBean(ServiceInstanceConverter.class)
    public ServiceInstanceConverter instanceConverter() {
        return new DefaultServiceInstanceConverter();
    }

    @Bean
    @ConditionalOnMissingBean(HttpHeadersProvider.class)
    public HttpHeadersProvider httpHeadersProvider() {
        return new BasicAuthHttpHeaderProvider();
    }

    @Bean
    @ConditionalOnMissingBean(ApplicationRegistry.class)
    public ApplicationRegistry applicationRegistry(ApplicationStore applicationStore,
                                                   DiscoveryClient discoveryClient,
                                                   ServiceInstanceConverter instanceConverter,
                                                   TaskScheduler taskScheduler,
                                                   TarocoAdminServerProperties serverProperties,
                                                   RestTemplateBuilder restTemplBuilder) {
        RestTemplateBuilder builder = restTemplBuilder.messageConverters(new MappingJackson2HttpMessageConverter())
                .errorHandler(new DefaultResponseErrorHandler() {
                    @Override
                    protected boolean hasError(HttpStatus statusCode) {
                        return false;
                    }
                });
        builder = builder.setConnectTimeout(serverProperties.getMonitor().getConnectTimeout())
                .setReadTimeout(serverProperties.getMonitor().getReadTimeout());

        return new ApplicationRegistry(applicationStore, discoveryClient, instanceConverter,
                taskScheduler, serverProperties, builder.build());
    }

    @EventListener
    public void onClientApplicationRegistered(ClientApplicationRegisteredEvent event) {
        publisher.publishEvent(new RoutesOutdatedEvent());
    }

    @EventListener
    public void onClientApplicationDeregistered(ClientApplicationDeregisteredEvent event) {
        publisher.publishEvent(new RoutesOutdatedEvent());
    }

    /**
     * Spring容器启动成功,开启服务监听
     */
    @EventListener
    public void onApplicationReady(ApplicationReadyEvent applicationReadyEvent) {
        ApplicationRegistry registry = applicationReadyEvent.getApplicationContext().getBean(ApplicationRegistry.class);
        registry.initInterval();
    }

    /**
     * 服务状态变更
     */
    @EventListener
    public void onClientApplicationStatusChangedEvent(ClientApplicationStatusChangedEvent statusChangedEvent) {
        StatusInfo from = statusChangedEvent.getFrom();
        StatusInfo to = statusChangedEvent.getTo();
        ApplicationStore store = applicationStore();
        Application app = statusChangedEvent.getApplication();
        app.getInstance().setStatus(to.getStatus());
        if (from.isUp() && !to.isUp()) {
            store.removeToDownMap(app);
        }
        if (to.isUp() && !from.isUp()) {
            store.removeToUP(app);
        }
    }

}

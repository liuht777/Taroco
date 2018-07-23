package cn.taroco.admin.registry.store;

import cn.taroco.admin.event.ClientApplicationDeregisteredEvent;
import cn.taroco.admin.event.ClientApplicationRegisteredEvent;
import cn.taroco.admin.model.Application;
import cn.taroco.admin.model.StatusInfo;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * 服务缓存hashMap实现
 *
 * @author liuht
 */
public class SimpleApplicationStore implements ApplicationStore, ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    private final ConcurrentMap<String, Application> map = new ConcurrentHashMap<>();

    private final ConcurrentMap<String, Application> downMap = new ConcurrentHashMap<>();

    @Override
    public Map getSumMap() {
        Map<String, Object> sum = new HashMap<>(3);
        sum.put("total", map.size() + downMap.size());
        sum.put("up", map.size());
        sum.put("down", downMap.size());
        return sum;
    }

    @Override
    public Application save(Application app) {
        if (!map.containsKey(app.getInstance().getInstanceId())
                && !downMap.containsKey(app.getInstance().getInstanceId())) {
            publisher.publishEvent(new ClientApplicationRegisteredEvent(app));
        }
        if (downMap.containsKey(app.getInstance().getInstanceId())) {
            if (StatusInfo.valueOf(app.getInstance().getStatus()).isUp()) {
                removeToUP(app);
                return app;
            } else {
                return downMap.put(app.getInstance().getInstanceId(), app);
            }
        }
        return map.put(app.getInstance().getInstanceId(), app);
    }

    @Override
    public Collection<Application> findAll() {
        Collection<Application> result = new ArrayList<>(12);
        if (!CollectionUtils.isEmpty(map.values())) {
            result.addAll(map.values());
        }
        if (!CollectionUtils.isEmpty(downMap.values())) {
            result.addAll(downMap.values());
        }
        return result;
    }

    @Override
    public Application find(String instanceId) {
        return map.get(instanceId) == null ? downMap.get(instanceId) : map.get(instanceId);
    }

    @Override
    public Collection<Application> findByNameAndStatus(String serviceId, String status) {
        List<Application> result = (List<Application>) findAll();
        if (!StringUtils.isEmpty(serviceId)) {
            result = result
                    .stream()
                    .filter(application -> serviceId.equals(application.getName()))
                    .collect(Collectors.toList());
        }
        if (!StringUtils.isEmpty(status)) {
            result = result
                    .stream()
                    .filter(application -> status.equalsIgnoreCase(application.getInstance().getStatus()))
                    .collect(Collectors.toList());
        }

        return result;
    }

    @Override
    public Application delete(String instanceId) {
        if (map.containsKey(instanceId)) {
            publisher.publishEvent(new ClientApplicationDeregisteredEvent(map.get(instanceId)));
            return map.remove(instanceId);
        }

        if (downMap.containsKey(instanceId)) {
            publisher.publishEvent(new ClientApplicationDeregisteredEvent(downMap.get(instanceId)));
            return downMap.remove(instanceId);
        }

        return null;
    }

    @Override
    public void removeToDownMap(Application app) {
        String instanceId = app.getInstance().getInstanceId();
        if (map.containsKey(instanceId)) {
            map.remove(instanceId);
            downMap.put(instanceId, app);
        }
    }

    @Override
    public void removeToUP(Application app) {
        if (downMap.containsKey(app.getInstance().getInstanceId())) {
            downMap.remove(app.getInstance().getInstanceId());
            map.put(app.getInstance().getInstanceId(), app);
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}

/*
 * Copyright 2013-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.taroco.admin.registry.store;

import cn.taroco.admin.model.Application;

import java.util.Collection;
import java.util.Map;

/**
 * 服务缓存接口
 *
 * @author liuht
 */
public interface ApplicationStore {

    /**
     * 获取服务统计信息
     *
     * @return 返回统计map
     */
    Map getSumMap();

    /**
     * 保存 服务
     * 保存之前要检查服务是否已经Down掉的map当中
     *
     * @param app 需要保存的服务
     * @return 保存失败/成功
     */
    Application save(Application app);

    /**
     * 返回所有服务列表,包括down掉的服务
     *
     * @return Collection<Application>
     */
    Collection<Application> findAll();

    /**
     * 根据实例id返回服务,也要在downmap中找
     *
     * @param instanceId 实例id
     * @return Application
     */
    Application find(String instanceId);

    /**
     * 根据服务名称 返回服务列表, 也要在downmap中找
     *
     * @param serviceId 服务名称
     * @param status    服务状态
     * @return Collection<Application>
     */
    Collection<Application> findByNameAndStatus(String serviceId, String status);

    /**
     * 根据实例id 删除服务, 也要在downmap中找
     *
     * @param instanceId 实例id
     * @return 成功/失败
     */
    Application delete(String instanceId);

    /**
     * 将down掉的服务移动到 downMap
     *
     * @param app 实例
     */
    void removeToDownMap(Application app);

    /**
     * 将UP的服务从dowmmap移出
     *
     * @param app 实例
     */
    void removeToUP(Application app);
}

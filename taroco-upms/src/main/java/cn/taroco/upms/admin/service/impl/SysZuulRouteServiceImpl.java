/*
 *    Copyright (c) 2018-2025, liuht All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: liuht (wangiegie@gmail.com)
 */

package cn.taroco.upms.admin.service.impl;

import cn.taroco.common.constants.CommonConstant;
import cn.taroco.common.entity.SysZuulRoute;
import cn.taroco.upms.admin.mapper.SysZuulRouteMapper;
import cn.taroco.upms.admin.service.SysZuulRouteService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 动态路由配置表 服务实现类
 * </p>
 *
 * @author liuht
 * @since 2018-05-15
 */
@Service
public class SysZuulRouteServiceImpl extends ServiceImpl<SysZuulRouteMapper, SysZuulRoute> implements SysZuulRouteService {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 立即生效配置
     *
     * @return
     */
    @Override
    public Boolean applyZuulRoute() {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
        List<SysZuulRoute> routeList = selectList(wrapper);
        redisTemplate.opsForValue().set(CommonConstant.ROUTE_KEY, routeList);
        //rabbitTemplate.convertAndSend(MqQueueConstant.ROUTE_CONFIG_CHANGE, 1);
        return Boolean.TRUE;
    }
}

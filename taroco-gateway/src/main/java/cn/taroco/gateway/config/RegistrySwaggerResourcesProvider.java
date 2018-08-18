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
 * Author: liuht
 */

package cn.taroco.gateway.config;

import cn.taroco.common.constants.ServiceNameConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuht
 * @date 2017/12/28
 * routeLocator 聚合swagger
 */
@Component
@Primary
public class RegistrySwaggerResourcesProvider implements SwaggerResourcesProvider {
    private final RouteLocator routeLocator;

    public RegistrySwaggerResourcesProvider(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();

        List<Route> routes = routeLocator.getRoutes();
        routes.forEach(route -> {
            //授权不维护到swagger
            if (!StringUtils.contains(route.getId(), ServiceNameConstants.AUTH_SERVICE)){
                resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs")));
            }
        });

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}

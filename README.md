# Taroco [演示地址](http://111.231.192.110)

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/liuht777/Taroco)

## 前言

'Taroco'是一整套的微服务企业级解决方案。是微服务敏捷开发的代表。

## 项目介绍

[Spring Cloud](https://projects.spring.io/spring-cloud/)是一个微服务框架，相比Dubbo等RPC框架, Spring Cloud提供更全面的分布式系统解决方案。Spring 
Cloud对微服务基础框架Netflix的多个开源组件进行了封装，同时又实现了和云端平台以及和Spring Boot开发框架的集成。Spring 
Cloud为微服务架构开发涉及的统一认证，配置管理，服务治理，熔断机制，动态路由等提供了一种简单的开发方式。

Spring Cloud本身已经封装得足够简单，也够丰富。也许正是因为这种简单而丰富，使得想要使用它的团队望而却步。学习成本太高，历史包袱太重，维护成本太高等等一系列原因。

*Taroco*就是为了解决这一问题而诞生的。*Taroco*整合了Spring Cloud的**配置中心**、**注册中心**、**服务网关**，提供了一系列starter组件，
同时提供**服务治理**、**服务监控**、**OAuth2权限认证**，支持**服务降级/熔断**、**基于标签(x-label)
的路由**、**服务权重**，前端采用**vue+elementUI**，可以很好的解决转向Spring Cloud的一系列问题，努力打造全方位微服务企业级敏捷开发解决方案。

*Taroco*提供了基于Docker Compose的部署方式。配置文件统一放置在docs目录中，运行脚本案例在根目录中查找。

### 主要实现功能
* Spring Cloud Eureka注册中心
* Spring Cloud Config Server配置中心，统一管理配置信息
* Spring Cloud Zuul Gateway统一微服务网关配置，支持动态路由配置
* 基于spring-boot-starter-actuator的自己实现的服务治理。包括日志、变量、映射等情况。
* 基于Hystrix的聚合监控页面，包括单个服务和服务多个实例的监控。
* 基于Spring Security OAuth2的权限认证系统。采用JWT RSA非对称加密的形式进行token加密解密。
* 支持基于权重以及基于标签的服务路由，支持动态配置服务权重及标签信息。通过控制用户标签以及动态路由的配置，满足各种各样请求策略。
* 基于角色的RBAC权限控制。支持数据权限控制。

### 开发环境

* JDK1.8+
* Spring Boot 1.5.12
* Spring Cloud Edgware.SR4
* Maven 3.0+
* Redis 3.0+
* MySQL 5.7
* Node.js 8+
* Npm 5+
* vue 2.0+

### 架构图

![架构图](taroco-docs/files/taroco架构图.jpg)

### 链接推荐

- Spring Boot 1.5.12.RELEASE官方文档 [https://docs.spring.io/spring-boot/docs/1.5.12.RELEASE/reference/htmlsingle/]
(https://docs.spring.io/spring-boot/docs/1.5.12.RELEASE/reference/htmlsingle/ "Spring Boot")
- Spring Cloud Edgware.SR4官方文档 [http://cloud.spring.io/spring-cloud-static/Dalston.SR4/multi/multi_spring-cloud.html](http://cloud.spring.io/spring-cloud-static/Edgware.SR4/multi/multi_spring-cloud.html "Spring Cloud")

### 资源下载

- JDK8 [http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html "JDK8")
- Maven [http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi "Maven")
- Redis [https://redis.io/download](https://redis.io/download "Redis")
- ActiveMQ [http://activemq.apache.org/download-archives.html](http://activemq.apache.org/download-archives.html "ActiveMQ")
- ZooKeeper [http://www.apache.org/dyn/closer.cgi/zookeeper/](http://www.apache.org/dyn/closer.cgi/zookeeper/ "ZooKeeper")
- Elastic Stack [https://www.elastic.co/downloads](https://www.elastic.co/downloads "Elastic Stack")
- Nginx [http://nginx.org/en/download.html](http://nginx.org/en/download.html "Nginx")
- Jenkins [http://updates.jenkins-ci.org/download/war/](http://updates.jenkins-ci.org/download/war/ "Jenkins")

## LICENSE

[MIT](LICENSE "MIT")

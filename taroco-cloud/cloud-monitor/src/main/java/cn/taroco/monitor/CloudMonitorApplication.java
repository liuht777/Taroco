package cn.taroco.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * 服务监控
 *
 * @author liuht
 * @date 2017/11/28 11:35
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTurbine
@EnableHystrixDashboard
public class CloudMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudMonitorApplication.class, args);
    }
}

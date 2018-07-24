package cn.taroco.oauth2.resource.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * auth2 启动类
 *
 * @author liuht
 * @date 2018/7/23 10:12
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ResourceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceDemoApplication.class, args);
    }
}

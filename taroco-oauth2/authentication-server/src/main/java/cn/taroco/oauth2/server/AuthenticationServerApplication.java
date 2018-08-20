package cn.taroco.oauth2.server;

import cn.taroco.oauth2.config.annotation.EnableAuthJwtTokenStore;
import cn.taroco.oauth2.config.annotation.EnableAuthRedisTokenStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


/**
 * auth2 启动类
 *
 * @author liuht
 * @date 2018/7/23 10:12
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAuthJwtTokenStore
@EnableAuthRedisTokenStore
@EnableFeignClients
public class AuthenticationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServerApplication.class, args);
    }
}

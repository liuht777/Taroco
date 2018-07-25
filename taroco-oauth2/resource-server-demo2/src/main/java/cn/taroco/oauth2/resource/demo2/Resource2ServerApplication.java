package cn.taroco.oauth2.resource.demo2;

import cn.taroco.oauth2.config.annotation.EnableAuthRedisTokenStore;
import cn.taroco.oauth2.config.annotation.EnableResourceJwtTokenStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Resource1Server 启动类
 * {@link EnableResourceJwtTokenStore} 使用 JWT 解析令牌
 *
 * @author liuht
 * @date 2018/7/23 10:12
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAuthRedisTokenStore
public class Resource2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Resource2ServerApplication.class, args);
    }
}

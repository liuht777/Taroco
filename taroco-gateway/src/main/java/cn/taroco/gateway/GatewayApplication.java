package cn.taroco.gateway;

import cn.taroco.gateway.core.GenericZuulFallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Bean;

/**
 * zuul gateway
 *
 * @author liuht
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

    /**
     * 定义路由 taroco-admin Fallback
     * @return ZuulFallbackProvider
     */
	@Bean
	public ZuulFallbackProvider zuulFallbackProvider() {
		GenericZuulFallbackProvider adminFallback = new GenericZuulFallbackProvider();
		adminFallback.setRoute("taroco-admin");
		return  adminFallback;
	}
}

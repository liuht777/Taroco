package cn.taroco.common.redis.limit.config;

import cn.taroco.common.redis.constant.RedisToolsConstant;
import cn.taroco.common.redis.limit.RedisLimit;
import cn.taroco.common.redis.limit.intercept.SpringMvcIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * redis 请求限流自动配置
 *
 * @author liuht
 * @date 2018/5/21 14:11
 */
@Configuration
@EnableConfigurationProperties(RedisLimitProperties.class)
@ConditionalOnProperty(name = "taroco.redis.limit.enabled", havingValue = "true")
public class RedisLimitAutoConfigurer extends WebMvcConfigurerAdapter {


    @Autowired
    private RedisLimitProperties limitProperties;

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public RedisLimit redisLimit() {
        return new RedisLimit.Builder(jedisConnectionFactory, RedisToolsConstant.SINGLE)
                .limit(limitProperties.getValue())
                .build();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SpringMvcIntercept(redisLimit()));
        super.addInterceptors(registry);
    }
}

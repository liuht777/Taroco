package cn.taroco.common.redis;

import cn.taroco.common.redis.template.TarocoRedisRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis 配置类
 *
 * @author liuht
 * @date 2017/11/6 11:02
 */
@Configuration
@ConditionalOnClass(TarocoRedisRepository.class)
@EnableConfigurationProperties(RedisProperties.class)
public class TarocoRedisAutoConfigure {

    /**
     * Redis repository redis repository.
     *
     * @param redisTemplate the redis template
     * @return the redis repository
     */
    @Bean
    @ConditionalOnMissingBean
    public TarocoRedisRepository redisRepository(RedisTemplate<String, String> redisTemplate) {
        return new TarocoRedisRepository(redisTemplate);
    }
}

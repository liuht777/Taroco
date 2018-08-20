package cn.taroco.oauth2.config.store;

import cn.taroco.common.constants.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 认证服务器使用Redis存取令牌
 * 注意: 需要配置redis参数
 *
 * @author liuht
 * @date 2018/7/25 9:36
 */
public class AuthRedisTokenStore {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 如果使用的 redis-cluster 模式请使用 TarocoRedisTokenStore
     *
     * @return TokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(SecurityConstants.TAROCO_PREFIX);
        return tokenStore;
    }
}

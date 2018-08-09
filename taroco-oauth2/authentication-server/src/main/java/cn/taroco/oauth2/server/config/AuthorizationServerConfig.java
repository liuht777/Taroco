package cn.taroco.oauth2.server.config;

import cn.taroco.oauth2.config.AbstractAuthServerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.concurrent.TimeUnit;

/**
 * OAuth2 授权服务器配置
 *
 * @author liuht
 * @date 2018/7/24 17:01
 */
@Configuration
@Order(Integer.MIN_VALUE)
public class AuthorizationServerConfig extends AbstractAuthServerConfig {

    /**
     * 调用父类构造函数，设置令牌失效日期等信息
     */
    public AuthorizationServerConfig() {
        super((int) TimeUnit.MINUTES.toSeconds(30), (int) TimeUnit.HOURS.toSeconds(1), true, true);
    }
}

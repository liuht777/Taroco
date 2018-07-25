package cn.taroco.oauth2.server.config;

import cn.taroco.oauth2.config.AbstractAuthServerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;

import java.util.concurrent.TimeUnit;

/**
 * OAuth2 授权服务器配置
 *
 * @author liuht
 * @date 2018/7/24 17:01
 */
@Configuration
public class AuthorizationServerConfig extends AbstractAuthServerConfig {

    /**
     * 调用父类构造函数，设置令牌失效日期等信息
     */
    public AuthorizationServerConfig() {
        super((int) TimeUnit.MINUTES.toSeconds(30), (int) TimeUnit.DAYS.toSeconds(1), true, true);
    }

    /**
     * 配置客户端详情
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
        clients
                // 使用内存存储客户端信息
                .inMemory()
                // client_id
                .withClient("resource1")
                // client_secret
                .secret("secret")
                // 该client允许的授权类型
                .authorizedGrantTypes("authorization_code", "password", "refresh_token", "client_credentials")
                // 允许的授权范围
                .scopes("read", "write")
                //登录后绕过批准询问(/oauth/confirm_access
                .autoApprove(true);
    }

}

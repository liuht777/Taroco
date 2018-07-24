package cn.taroco.auth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 认证服务配置
 * 声明一个认证服务器，当用此注解后，应用启动后将自动生成几个Endpoint.
 * 注：其实实现一个认证服务器就是这么简单，加一个注解就搞定，当然真正用到生产环境还是要进行一些配置和复写工作的。
 * /oauth/authorize：验证
 * /oauth/token：获取token
 * /oauth/confirm_access：用户授权
 * /oauth/error：认证失败
 * /oauth/check_token：资源服务器用来校验token
 * /oauth/token_key：如果jwt模式则可以用此来从认证服务器获取公钥
 *
 * @author liuht
 * @date 2018/7/23 12:52
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private static String REALM = "TAROCO_OAUTH_REALM";

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserApprovalHandler userApprovalHandler;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    /**
     * client客户端的信息配置
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 能够使用内存或者JDBC来实现客户端详情服务（ClientDetailsService)
        clients.inMemory()
                // (必须的）用来标识客户的Id。
                .withClient("client1")
                // 此客户端可以使用的授权类型，默认为空。
                // authorization_code：用验证获取code，再用code去获取token（用的最多的方式，也是最安全的方式）
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "client_credentials")
                // 此客户端可以使用的权限（基于Spring Security authorities）。
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                // 用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。
                .scopes("read", "write")
                // 需要值得信任的客户端）客户端安全码，如果有的话。
                .secret("secret")
                // token有效期为1800秒
                .accessTokenValiditySeconds(1800)
                // 刷新token有效期为3600秒
                .refreshTokenValiditySeconds(3600);
    }

    /**
     * 声明授权和token的端点以及token的服务的一些配置信息，比如采用什么存储方式、token的有效期等
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore)
                .userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager);
    }

    /**
     * 声明安全约束，哪些允许访问，哪些不允许访问
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                //.realm(REALM + "/client")
                .allowFormAuthenticationForClients();
    }
}

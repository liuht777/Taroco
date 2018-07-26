package cn.taroco.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 资源服务器配置
 *
 * @author liuht
 * @date 2018/7/24 16:15
 */
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class AbstractResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired(required = false)
    private RemoteTokenServices remoteTokenServices;

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @Bean
    @Qualifier("authorizationHeaderRequestMatcher")
    public RequestMatcher authorizationHeaderRequestMatcher() {
        return new RequestHeaderRequestMatcher("Authorization");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                // 禁用csrf
                .csrf().disable()
                // 禁用x-frame-options 即是: 允许使用iframe
                .headers().frameOptions().disable()
                // Spring不使用HTTPSession 默认对所有请求都做权限校验
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 请求必须包含认证头部"Authorization"
                .and().requestMatcher(authorizationHeaderRequestMatcher());
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        if (StringUtils.isEmpty(oAuth2ClientProperties.getClientId())) {
            resources.resourceId(oAuth2ClientProperties.getClientId());
        }
        if (Objects.nonNull(remoteTokenServices)) {
            resources.tokenServices(remoteTokenServices);
        }
    }
}

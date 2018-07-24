package cn.taroco.auth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * 资源服务器配置
 *
 * @author liuht
 * @date 2018/7/23 12:56
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "taroco_rest_api";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                //resourceId 用于分配给可授予的clientId
                .resourceId(RESOURCE_ID)
                //stateless  标记以指示在这些资源上仅允许基于令牌的身份验证
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
                .and()
                .logout()
                .logoutUrl("/oauth/logout")
                .logoutSuccessUrl("/logout/successed")
                .and()
                .authorizeRequests()
                // 不登陆也可以访问的url
                .antMatchers("/hello").permitAll()
                .antMatchers("/oauth/*").permitAll()
                .antMatchers("/security").authenticated();
                //.antMatchers("/**").authenticated();
    }
}

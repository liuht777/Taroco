package cn.taroco.oauth2.server.config;

import cn.taroco.oauth2.config.AbstractSecurityConfig;
import cn.taroco.oauth2.config.FilterIgnorePropertiesConfig;
import cn.taroco.oauth2.server.component.mobile.MobileSecurityConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * WebSecurity配置类
 *
 * @author liuht
 * @date 2018/7/24 15:53
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER - 1)
@EnableConfigurationProperties(FilterIgnorePropertiesConfig.class)
public class WebSecurityConfig extends AbstractSecurityConfig {

    @Autowired
    private FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;

    @Autowired
    private MobileSecurityConfigurer mobileSecurityConfigurer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
                http.formLogin().loginPage("/authentication/require")
                        .loginProcessingUrl("/authentication/form")
                        .and()
                        .authorizeRequests();
        filterIgnorePropertiesConfig
                .getUrls()
                .forEach(url -> registry.antMatchers(url).permitAll());
        registry.anyRequest().authenticated()
                .and()
                .csrf().disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.apply(mobileSecurityConfigurer);
    }
}

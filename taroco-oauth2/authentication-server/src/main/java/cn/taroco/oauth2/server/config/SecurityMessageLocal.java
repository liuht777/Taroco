package cn.taroco.oauth2.server.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Security message
 *
 * @author liuht
 * @date 2018/7/30 17:32
 */
@Configuration
public class SecurityMessageLocal {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.addBasenames("classpath:messages/security/messages_zh_CN");
        return messageSource;
    }
}

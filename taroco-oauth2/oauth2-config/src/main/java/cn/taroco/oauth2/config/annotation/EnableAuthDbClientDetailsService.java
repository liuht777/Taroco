package cn.taroco.oauth2.config.annotation;

import cn.taroco.oauth2.config.service.AuthDbClientDetailsService;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 认证服务器:在启动类上添加该注解来----开启从数据库加载客户端详情
 *
 * @author liuht
 * @date 2018/7/24 16:42
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AuthDbClientDetailsService.class)
public @interface EnableAuthDbClientDetailsService {
}

package cn.taroco.oauth2.config.annotation;

import cn.taroco.oauth2.config.service.ResourceRemoteTokenService;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 资源服务器:在启动类上添加该注解来----开启通过授权服务器验证访问令牌（适用于 JDBC、内存存储令牌）
 *
 * @author liuht
 * @date 2018/7/24 16:48
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ResourceRemoteTokenService.class)
public @interface EnableResourceRemoteTokenService {
}

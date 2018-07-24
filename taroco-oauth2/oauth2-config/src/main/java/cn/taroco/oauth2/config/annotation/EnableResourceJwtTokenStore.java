package cn.taroco.oauth2.config.annotation;

import cn.taroco.oauth2.config.store.ResourceJwtTokenStore;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在启动类上添加该注解来----开启 JWT 令牌存储（资源服务器-非对称加密）
 *
 * @author liuht
 * @date 2018/7/24 16:50
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ResourceJwtTokenStore.class)
public @interface EnableResourceJwtTokenStore {
}

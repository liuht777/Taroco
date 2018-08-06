package cn.taroco.oauth2.server.controller;

import cn.taroco.common.constants.SecurityConstants;
import cn.taroco.common.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liuht
 * @date 2018年03月10日
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    /**
     * 认证页面
     *
     * @return ModelAndView
     */
    @GetMapping("/require")
    public ModelAndView require() {
        return new ModelAndView("ftl/login");
    }

    @PostMapping("/loginSuccess")
    public Response loginSuccess(Authentication authentication) {
        Response response = Response.success(authentication);
        response.setExtMessage("登录成功");
        return response;
    }

    /**
     * 用户信息校验
     *
     * @param authentication 信息
     * @return 用户信息
     */
    @RequestMapping("/user")
    public Object user(Authentication authentication) {
        return authentication.getPrincipal();
    }

    /**
     * 清除Redis中 accesstoken refreshtoken
     *
     * @param accesstoken accesstoken
     * @return true/false
     */
    @PostMapping("/removeToken")
    @CacheEvict(value = SecurityConstants.TOKEN_USER_DETAIL, key = "#accesstoken")
    public Response removeToken(String accesstoken) {
        return Response.success(consumerTokenServices.revokeToken(accesstoken));
    }
}

package cn.taroco.auth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 登出
 *
 * @author liuht
 * @date 2018/7/24 8:16
 */
@RestController
public class LogoutController {
    private static final String BEARER_AUTHENTICATION = "Bearer ";
    private static final String HEADER_AUTHORIZATION = "authorization";

    @Autowired
    private TokenStore tokenStore;


    /**
     * 登出成功回调url
     *
     * @param request 请求对象
     * @return 登出成功
     */
    @RequestMapping("/logout/successed")
    public Object logoutSuccess(HttpServletRequest request) {
        String token = request.getHeader(HEADER_AUTHORIZATION);
        if (token != null && token.startsWith(BEARER_AUTHENTICATION)) {
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token.split(" ")[0]);
            if (oAuth2AccessToken != null) {
                tokenStore.removeAccessToken(oAuth2AccessToken);
            }
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}

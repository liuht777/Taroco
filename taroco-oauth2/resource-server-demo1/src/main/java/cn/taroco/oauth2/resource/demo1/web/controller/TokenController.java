package cn.taroco.oauth2.resource.demo1.web.controller;

import cn.taroco.oauth2.resource.demo1.web.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Base64;
import java.util.Collections;

/**
 * 令牌管理接口
 *
 * @author liuht
 * @date 2018/7/24 17:49
 */
@RestController
public class TokenController {

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @Autowired
    private OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 通过密码授权方式向授权服务器获取令牌
     *
     * @param loginDTO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/login")
    public ResponseEntity<OAuth2AccessToken> login(@RequestBody @Valid LoginDTO loginDTO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("登录信息格式错误");
        } else {
            //Http Basic 验证
            String clientAndSecret = oAuth2ClientProperties.getClientId() + ":" + oAuth2ClientProperties.getClientSecret();
            //这里需要注意为 Basic 而非 Bearer
            clientAndSecret = "Basic " + Base64.getEncoder().encodeToString(clientAndSecret.getBytes());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", clientAndSecret);
            //授权请求信息
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.put("username", Collections.singletonList(loginDTO.getUsername()));
            map.put("password", Collections.singletonList(loginDTO.getPassword()));
            map.put("grant_type", Collections.singletonList(oAuth2ProtectedResourceDetails.getGrantType()));
            map.put("scope", oAuth2ProtectedResourceDetails.getScope());
            //HttpEntity
            HttpEntity httpEntity = new HttpEntity(map, httpHeaders);
            //获取 Token
            return restTemplate.exchange(oAuth2ProtectedResourceDetails.getAccessTokenUri(), HttpMethod.POST, httpEntity, OAuth2AccessToken.class);
        }
    }

}

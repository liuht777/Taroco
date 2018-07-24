package cn.taroco.oauth2.resource.demo1.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * 资源服务器1-资源接口
 *
 * @author liuht
 * @date 2018/7/24 17:46
 */
@RestController
public class ResController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/res")
    public ResponseEntity<String> res() {
        return ResponseEntity.ok("<h1>这是资源服务器1的受保护的资源</h1>");
    }

    /**
     * 访问资源服务器2-资源接口
     *
     * @param httpReq
     * @return
     */
    @GetMapping("/res2/res")
    public ResponseEntity<String> remoteRes(HttpServletRequest httpReq) {
        //HttpEntity
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", httpReq.getHeader("Authorization"));
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        //请求资源服务器2的资源
        return restTemplate.exchange("http://localhost:9003/res", HttpMethod.GET, httpEntity, String.class);
    }

}

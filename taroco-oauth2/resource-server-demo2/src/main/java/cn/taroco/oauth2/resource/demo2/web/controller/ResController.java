package cn.taroco.oauth2.resource.demo2.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资源服务器1-资源接口
 *
 * @author liuht
 * @date 2018/7/24 17:46
 */
@RestController
public class ResController {

    @GetMapping("/res2/res")
    public ResponseEntity<String> res() {
        return ResponseEntity.ok("<h1>这是资源服务器2的受保护的资源</h1>");
    }

}

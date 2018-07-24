package cn.taroco.auth2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author liuht
 * @date 2018/7/24 8:33
 */
@RestController
public class TestController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/security")
    public String security() {
        return "Hello Security!";
    }
}

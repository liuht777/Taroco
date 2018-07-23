package cn.taroco.demob.service;

import cn.taroco.common.web.Response;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 描述
 *
 * @author liuht
 * @date 2017/11/22 15:25
 */
@Service
public class TestServiceB {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fail")
    public Response getDemoA() {
        return restTemplate.getForObject("http://TAROCO-DEMOB-A/test", Response.class);
    }

    public Response fail() {
        return Response.failure("请求失败");
    }

    @HystrixCommand(fallbackMethod = "fail1")
    public String test1() {
        return "1111";
    }

    public String fail1() {
        return "请求失败";
    }
}

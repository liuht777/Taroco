package cn.taroco.demob.web;

import cn.taroco.common.web.Response;
import cn.taroco.demob.service.TestServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author liuht
 * @date 2017/11/22 15:23
 */
@RestController
@RequestMapping("/test")
public class HystrixTemplateTest {

    @Autowired
    private TestServiceB testService;

    @RequestMapping(method = RequestMethod.GET)
    public Response test1() {
        return Response.success("来自于与demoB的返回");
    }

    @RequestMapping(value = "/1", method = RequestMethod.GET)
    public Response test11() {
        return Response.success(testService.test1());
    }
}

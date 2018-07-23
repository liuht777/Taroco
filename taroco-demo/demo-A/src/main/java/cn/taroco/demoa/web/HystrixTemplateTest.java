package cn.taroco.demoa.web;

import cn.taroco.common.web.Response;
import cn.taroco.demoa.service.TestServiceA;
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
    private TestServiceA testService;

    @RequestMapping(method = RequestMethod.GET)
    public Response test1() {
        return testService.getDemoB();
    }
}

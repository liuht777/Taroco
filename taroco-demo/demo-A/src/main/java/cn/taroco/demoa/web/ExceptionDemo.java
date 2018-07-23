package cn.taroco.demoa.web;

import cn.taroco.common.exception.BusinessException;
import cn.taroco.common.web.Response;
import cn.taroco.demoa.exception.DemoAError;
import cn.taroco.demoa.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明：异常使用
 *
 * @author zhangwei
 * @version 2017/11/23 14:12.
 */
@Api(tags = "exception", description = "异常使用demo")
@RestController
@RequestMapping("exception")
public class ExceptionDemo {

    @ApiOperation("异常")
    @GetMapping("error")
    public void error(){
        throw new BusinessException(DemoAError.USER_IS_EXIST);
    }

    @ApiOperation("成功")
    @GetMapping("success")
    public Response success(){
        UserVo userVo = new UserVo();
        userVo.setUserName("username");
        userVo.setEnable(true);
        Response response = new Response();
        response.setResult(userVo);
        return response;
    }
}

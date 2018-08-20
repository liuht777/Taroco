package cn.taroco.common.web;

import cn.taroco.common.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author liuht
 * @date 2017/10/28
 */
public class BaseController {
    @Autowired
    private HttpServletRequest request;

    /**
     * 根据请求heard中的token获取用户角色
     *
     * @return 角色名
     */
    public List<String> getRole() {
        return UserUtils.getRole(request);
    }

    /**
     * 根据请求heard中的token获取用户ID
     *
     * @return 用户ID
     */
    public Integer getUserId() {
        return UserUtils.getUserId(request);
    }


}

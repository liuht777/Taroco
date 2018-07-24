package cn.taroco.oauth2.resource.demo1.web.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户登录数据传输对象
 *
 * @author liuht
 * @date 2018/7/24 17:44
 */
@Data
public class LoginDTO {

    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}

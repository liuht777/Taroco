package cn.taroco.rbac.admin.model.dto;

import cn.taroco.rbac.admin.model.entity.SysUser;
import lombok.Data;

import java.util.List;

/**
 * @author liuht
 * @date 2017/11/5
 */
@Data
public class UserDTO extends SysUser {
    /**
     * 角色ID
     */
    private List<Integer> role;

    private Integer deptId;

    /**
     * 新密码
     */
    private String newpassword1;
}

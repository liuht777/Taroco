package cn.taroco.rbac.admin.service;


import com.baomidou.mybatisplus.service.IService;
import cn.taroco.rbac.admin.model.entity.SysUserRole;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author liuht
 * @since 2017-10-29
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据用户Id删除该用户的角色关系
     *
     * @author 寻欢·李
     * @date 2017年12月7日 16:31:38
     * @param userId 用户ID
     * @return boolean
     */
    Boolean deleteByUserId(Integer userId);
}

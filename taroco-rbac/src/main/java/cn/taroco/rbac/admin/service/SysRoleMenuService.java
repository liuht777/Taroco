package cn.taroco.rbac.admin.service;


import cn.taroco.rbac.admin.model.entity.SysRoleMenu;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author liuht
 * @since 2017-10-29
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 更新角色菜单
     *
     *
     * @param role
     * @param roleId  角色
     * @param menuIds 菜单列表
     * @return
     */
    Boolean insertRoleMenus(String role, Integer roleId, Integer[] menuIds);
}

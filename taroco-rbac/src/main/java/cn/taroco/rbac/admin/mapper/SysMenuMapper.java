package cn.taroco.rbac.admin.mapper;

import cn.taroco.rbac.admin.model.entity.SysMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import cn.taroco.common.vo.MenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author liuht
 * @since 2017-10-29
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 通过角色名查询菜单
     *
     * @param role 角色名称
     * @return 菜单列表
     */
    List<MenuVO> findMenuByRoleName(@Param("role") String role);
}

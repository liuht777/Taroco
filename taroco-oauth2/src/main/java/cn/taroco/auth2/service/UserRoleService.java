package cn.taroco.auth2.service;

import java.util.List;

/**
 * UserRoleService
 *
 * @author liuht
 * @date 2018/7/23 12:45
 */
public interface UserRoleService {

    /**
     * 查询角色名称集合
     *
     * @param uid 用户id
     * @return 角色名称集合
     */
    List<String> findRoles(int uid);
}

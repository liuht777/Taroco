package cn.taroco.auth2.service;

import cn.taroco.auth2.entity.User;

import java.util.List;

/**
 * @author liuht
 * @date 2018/7/23 12:44
 */
public interface UserService {

    /**
     * 根据用户名查询
     *
     * @param name 用户名称
     * @return 用户详情
     */
    User findByUsername(String name);

    /**
     * 查询所有用户
     *
     * @return 所有用户
     */
    List<User> findAll();
}

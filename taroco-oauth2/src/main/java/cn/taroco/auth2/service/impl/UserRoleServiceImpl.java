package cn.taroco.auth2.service.impl;

import cn.taroco.auth2.mapper.UserRoleRepository;
import cn.taroco.auth2.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author liuht
 * @date 2018/7/23 12:50
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<String> findRoles(int uid) {

        return userRoleRepository.findRoleName(uid);
    }
}

package cn.taroco.auth2.service.impl;

import cn.taroco.auth2.entity.User;
import cn.taroco.auth2.mapper.UserRepository;
import cn.taroco.auth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuht
 * @date 2018/7/23 12:49
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}

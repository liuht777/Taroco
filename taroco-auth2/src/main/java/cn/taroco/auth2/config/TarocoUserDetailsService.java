package cn.taroco.auth2.config;

import cn.taroco.auth2.entity.Role;
import cn.taroco.auth2.entity.User;
import cn.taroco.auth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;

/**
 * 重写Spring Security　UserDetailsService
 *
 * @author liuht
 * @date 2018/7/23 12:36
 */
public class TarocoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * 根据用户名获取登录用户信息
     *
     * @param username 用户名
     * @return 用户兮兮
     * @throws UsernameNotFoundException 售电
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户名：" + username + "不存在！");
        }
        Collection<SimpleGrantedAuthority> collection = new HashSet<>();
        for (Role role : user.getList()) {
            collection.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), collection);
    }
}

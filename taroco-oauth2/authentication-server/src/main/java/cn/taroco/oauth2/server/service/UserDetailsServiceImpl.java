package cn.taroco.oauth2.server.service;

import cn.taroco.oauth2.config.constants.AuthoritiesEnum;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户信息获取
 *
 * @author liuht
 * @date 2018/7/24 17:06
 */
@Service("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * 通过 Username 加载用户详情
     * @param username 用户名
     * @return UserDetails
     * @throws UsernameNotFoundException 用户没找到
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("htliu".equals(username)) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode("123456");
            return new User(
                    "htliu",
                    password,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(AuthoritiesEnum.USER.getRole())
            );
        }
        return null;
    }

}

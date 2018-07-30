package cn.taroco.oauth2.server.service;

import cn.taroco.common.constants.CommonConstant;
import cn.taroco.common.vo.UserVO;
import cn.taroco.oauth2.config.util.UserDetailsImpl;
import cn.taroco.oauth2.server.feign.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户信息获取
 *
 * @author liuht
 * @date 2018/7/24 17:06
 */
@Service("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * 通过 Username 加载用户详情
     *
     * @param username 用户名
     * @return UserDetails
     * @throws UsernameNotFoundException 用户没找到
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO userVo = userService.findUserByUsername(username);
        if (userVo == null) {
            throw new UsernameNotFoundException("用户名/密码错误");
        }
        if (CommonConstant.DEL_FLAG.equals(userVo.getDelFlag())) {
            throw new DisabledException("用户: " + username + " 不可用");
        }
        return new UserDetailsImpl(userVo);
    }

}

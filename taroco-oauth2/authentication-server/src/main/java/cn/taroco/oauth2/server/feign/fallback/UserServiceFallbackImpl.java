package cn.taroco.oauth2.server.feign.fallback;

import cn.taroco.common.vo.UserVO;
import cn.taroco.oauth2.server.feign.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author liuht
 * @date 2017/10/31
 * 用户服务的fallback
 */
@Service
@Slf4j
public class UserServiceFallbackImpl implements UserService {

    @Override
    public UserVO findUserByUsername(String username) {
        log.error("通过用户名查询用户异常:{}", username);
        return new UserVO();
    }

    /**
     * 通过手机号查询用户、角色信息
     *
     * @param mobile 手机号
     * @return UserVo
     */
    @Override
    public UserVO findUserByMobile(String mobile) {
        log.error("通过手机号查询用户:{}", mobile);
        return new UserVO();
    }

    /**
     * 根据OpenId查询用户信息
     *
     * @param openId openId
     * @return UserVo
     */
    @Override
    public UserVO findUserByOpenId(String openId) {
        log.error("通过OpenId查询用户:{}", openId);
        return new UserVO();
    }
}

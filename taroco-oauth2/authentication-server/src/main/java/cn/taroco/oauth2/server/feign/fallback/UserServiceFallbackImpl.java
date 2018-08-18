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
        log.error("调用{}异常:{}", "通过用户名查询用户", username);
        return null;
    }

    /**
     * 通过手机号查询用户、角色信息
     *
     * @param mobile 手机号
     * @return UserVo
     */
    @Override
    public UserVO findUserByMobile(String mobile) {
        log.error("调用{}异常:{}", "通过手机号查询用户", mobile);
        return null;
    }

    /**
     * 根据OpenId查询用户信息
     *
     * @param openId openId
     * @return UserVo
     */
    @Override
    public UserVO findUserByOpenId(String openId) {
        log.error("调用{}异常:{}", "通过OpenId查询用户", openId);
        return null;
    }
}

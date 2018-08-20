package cn.taroco.gateway.feign.fallback;

import cn.taroco.common.vo.MenuVO;
import cn.taroco.gateway.feign.MenuService;
import com.xiaoleilu.hutool.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;


/**
 * @author liuht
 * @date 2017/10/31
 */
@Slf4j
@Service
public class MenuServiceFallbackImpl implements MenuService {
    @Override
    public Set<MenuVO> findMenuByRole(String role) {
        log.error("调用{}异常{}", "findMenuByRole", role);
        return CollUtil.newHashSet();
    }
}

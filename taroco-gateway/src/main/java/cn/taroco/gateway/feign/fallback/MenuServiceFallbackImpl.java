/*
 *    Copyright (c) 2018-2025, liuht All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: liuht
 */

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

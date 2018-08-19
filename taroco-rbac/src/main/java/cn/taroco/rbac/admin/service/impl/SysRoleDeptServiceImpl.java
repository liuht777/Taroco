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

package cn.taroco.rbac.admin.service.impl;

import cn.taroco.rbac.admin.model.entity.SysRoleDept;
import cn.taroco.rbac.admin.service.SysRoleDeptService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.taroco.rbac.admin.mapper.SysRoleDeptMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与部门对应关系 服务实现类
 * </p>
 *
 * @author liuht
 * @since 2018-01-20
 */
@Service
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptMapper, SysRoleDept> implements SysRoleDeptService {

}

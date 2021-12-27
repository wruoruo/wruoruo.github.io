package com.zsj.es.system.service.impl;

import com.zsj.es.system.dao.UserRoleMapper;
import com.zsj.es.system.model.UserRole;
import com.zsj.es.system.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户角色关联表 服务实现类
 *
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}

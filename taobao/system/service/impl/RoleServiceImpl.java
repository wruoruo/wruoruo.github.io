package com.zsj.es.system.service.impl;

import com.zsj.es.system.dao.RoleMapper;
import com.zsj.es.system.model.Role;
import com.zsj.es.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色表 服务实现类
 *
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<Role> listByUserId(Integer userId) {
        return baseMapper.listByUserId(userId);
    }

    @Override
    public Role getByUserId(Integer userId) {
        return baseMapper.getByUserId(userId);
    }
}

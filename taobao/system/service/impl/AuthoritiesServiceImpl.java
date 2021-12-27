package com.zsj.es.system.service.impl;

import com.zsj.es.system.dao.AuthoritiesMapper;
import com.zsj.es.system.model.Authorities;
import com.zsj.es.system.service.AuthoritiesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限表 服务实现类
 *
 */
@Service
public class AuthoritiesServiceImpl extends ServiceImpl<AuthoritiesMapper, Authorities> implements AuthoritiesService {

    @Override
    public List<Authorities> listByUserId(Integer userId) {
        return baseMapper.listByUserId(userId);
    }

    @Override
    public List<Authorities> listByRoleId(Integer roleId) {
        if (roleId == null) {
            return new ArrayList<>();
        }
        return baseMapper.listByRoleId(roleId);
    }


    @Override
    public List<Authorities> listByRoleIds(List<Integer> roleIds) {
        if (roleIds == null || roleIds.size() == 0) {
            return new ArrayList<>();
        }
        return baseMapper.listByRoleIds(roleIds);
    }

}

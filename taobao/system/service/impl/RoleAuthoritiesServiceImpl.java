package com.zsj.es.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zsj.es.common.exception.BusinessException;
import com.zsj.es.system.dao.RoleAuthoritiesMapper;
import com.zsj.es.system.model.RoleAuthorities;
import com.zsj.es.system.service.RoleAuthoritiesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色权限关联表 服务实现类
 *
 */
@Service
public class RoleAuthoritiesServiceImpl extends ServiceImpl<RoleAuthoritiesMapper, RoleAuthorities> implements RoleAuthoritiesService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateRoleAuth(Integer roleId, List<Integer> authIds) {
        baseMapper.delete(new UpdateWrapper<RoleAuthorities>().eq("role_id", roleId));
        if (authIds != null && authIds.size() > 0) {
            if (baseMapper.insertRoleAuths(roleId, authIds) < authIds.size()) {
                throw new BusinessException("操作失败");
            }
        }
        return true;
    }

}

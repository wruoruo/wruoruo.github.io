package com.zsj.es.system.service;

import com.zsj.es.system.model.RoleAuthorities;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色权限关联表 服务类
 *
 */
public interface RoleAuthoritiesService extends IService<RoleAuthorities> {
    boolean updateRoleAuth(Integer roleId, List<Integer> authIds);
}

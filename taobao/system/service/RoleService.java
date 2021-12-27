package com.zsj.es.system.service;

import com.zsj.es.system.model.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色表 服务类
 *
 */
public interface RoleService extends IService<Role> {

    List<Role> listByUserId(Integer userId);

    Role getByUserId(Integer userId);
}

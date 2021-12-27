package com.zsj.es.system.service;

import com.zsj.es.system.model.Authorities;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 权限表 服务类
 *
 */
public interface AuthoritiesService extends IService<Authorities> {

    List<Authorities> listByUserId(Integer userId);

    List<Authorities> listByRoleIds(List<Integer> roleIds);

    List<Authorities> listByRoleId(Integer roleId);
}

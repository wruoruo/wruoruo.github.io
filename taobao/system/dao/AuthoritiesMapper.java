package com.zsj.es.system.dao;

import com.zsj.es.system.model.Authorities;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 */
public interface AuthoritiesMapper extends BaseMapper<Authorities> {

    List<Authorities> listByUserId(Integer userId);

    List<Authorities> listByRoleIds(@Param("roleIds") List<Integer> roleIds);

    List<Authorities> listByRoleId(Integer roleId);
}

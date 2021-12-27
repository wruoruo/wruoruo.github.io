package com.zsj.es.system.dao;

import com.zsj.es.common.PageParam;
import com.zsj.es.system.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 */
public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(String username);

    User selectByPhone(String phone);

    List<User> listFull(@Param("page") PageParam page);

    /**
     * 获取当日新增用户
     */
    Integer selectNowAddUser();
}

package com.zsj.es.system.service;

import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.system.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户表 服务类
 *
 */
public interface UserService extends IService<User> {

    User getByUsername(String username);

    User getByPhone(String phone);

    PageResult<User> listUser(PageParam pageParam);

    boolean addUser(User user, List<Integer> roleIds);

    boolean updateUser(User user, List<Integer> roleIds);

    /**
     * 获取当日新增用户
     */
    Integer selectNowAddUser();

    boolean checkOnLineState(Integer userId);
}

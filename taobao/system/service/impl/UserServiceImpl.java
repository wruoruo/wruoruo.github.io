package com.zsj.es.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.common.exception.BusinessException;
import com.zsj.es.system.dao.UserMapper;
import com.zsj.es.system.dao.UserRoleMapper;
import com.zsj.es.system.model.Role;
import com.zsj.es.system.model.User;
import com.zsj.es.system.model.UserRole;
import com.zsj.es.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户表 服务实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User getByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }

    @Override
    public User getByPhone(String phone) {

        return baseMapper.selectByPhone(phone);
    }

    @Override
    public PageResult<User> listUser(PageParam pageParam) {
        List<User> userList = baseMapper.listFull(pageParam);
        // 查询user的角色
        if (userList != null && userList.size() > 0) {
            List<UserRole> userRoles = userRoleMapper.selectByUserIds(getUserIds(userList));
            for (User one : userList) {
                List<Role> tempURs = new ArrayList<>();
                for (UserRole ur : userRoles) {
                    if (one.getUserId().equals(ur.getUserId())) {
                        tempURs.add(new Role(ur.getRoleId(), ur.getRoleName(), null));
                    }
                }
                one.setRoles(tempURs);
            }
        }
        return new PageResult<>(userList, pageParam.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addUser(User user, List<Integer> roleIds) {
        if (baseMapper.selectByUsername(user.getUsername()) != null) {
            throw new BusinessException("账号" + user.getUsername() + "已经存在");
        }
        boolean result = baseMapper.insert(user) > 0;
        if (result) {
            if (userRoleMapper.insertBatch(user.getUserId(), roleIds) < roleIds.size()) {
                throw new BusinessException("添加失败");
            }
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(User user, List<Integer> roleIds) {
        user.setUsername(null);  // 账号不能修改
        boolean result = baseMapper.updateById(user) > 0;
        if (result) {
            userRoleMapper.delete(new UpdateWrapper<UserRole>().eq("user_id", user.getUserId()));
            if (userRoleMapper.insertBatch(user.getUserId(), roleIds) < roleIds.size()) {
                throw new BusinessException("修改失败");
            }
        }
        return result;
    }

    /**
     * 获取当日新增用户
     */
    @Override
    public Integer selectNowAddUser() {

        return baseMapper.selectNowAddUser();
    }

    @Override
    public boolean checkOnLineState(Integer userId) {
        User user=userMapper.selectById(userId);
        if(user.getUserOnlineType()==0){
            return false;
        }
        return true;
    }

    /**
     * 获取用户id
     */
    private List<Integer> getUserIds(List<User> userList) {
        List<Integer> userIds = new ArrayList<>();
        for (User one : userList) {
            userIds.add(one.getUserId());
        }
        return userIds;
    }

}

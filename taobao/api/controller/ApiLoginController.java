package com.zsj.es.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zsj.es.common.JsonResult;
import com.zsj.es.common.shiro.EndecryptUtil;
import com.zsj.es.common.utils.StringUtil;
import com.zsj.es.system.model.User;
import com.zsj.es.system.model.UserRole;
import com.zsj.es.system.service.UserRoleService;
import com.zsj.es.system.service.UserService;


/**
 * api登录相关接口
 */
@RequestMapping("/api")
@RestController
public class ApiLoginController{

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRoleService userRoleService;
    /**
     * 登录
     */
    @PostMapping("/login")
    public JsonResult login(String username, String password) {
        if (StringUtil.isBlank(username, password)) {
            return JsonResult.error("账号或密码不能为空");
        }
        User user = userService.getByUsername(username);
        if (user == null) {
            return JsonResult.error("账号不存在");
        }
        if (user.getState() != 0) {
            return JsonResult.error("账号被锁定");
        }
        if (!EndecryptUtil.encrytMd5(password, 3).equals(user.getPassword())) {
            return JsonResult.error("密码错误");
        }
        //登陆成功修改用户在线状态
        User u=new User();
        u.setUserId(user.getUserId());
        u.setUserOnlineType(1);
        userService.updateById(u);
        return JsonResult.ok("登录成功").put("user", user);
    }

    /**
     * 账号注册
     */
    @PostMapping("/register")
    public JsonResult register(User user) {
        User userinfo = userService.getByUsername(user.getUsername());
        if(userinfo!=null){
            return JsonResult.error("用户名已存在，请修改后提交");
        }
        user.setState(0);
        user.setUserType(1);
        //设置默认头像
        String avatar="http://thirdqq.qlogo.cn/g?b=oidb&k=pK18ebvJeCnGundmu9GoAQ&s=640&t=1571929112";
        user.setAvatar(avatar);
        user.setPassword(EndecryptUtil.encrytMd5(user.getPassword(), 3));
        if (userService.save(user)) {
        	UserRole userRole = new UserRole();
        	userRole.setUserId(user.getUserId());
        	userRole.setRoleId(2);
        	userRoleService.save(userRole);
            return JsonResult.ok("注册成功");
        }
        return JsonResult.error("注册失败");
    }

    /**
     * 根据用户id查询用户信息
     */
    @GetMapping("/getUserById")
    public JsonResult userInfo(User user) {

        return JsonResult.error("查询成功").put("data",userService.getById(user.getUserId()));
    }

    /**
     * 修改个人信息
     */
    @PostMapping("/updateMyInfo")
    public JsonResult updateMyInfo(User user) {
        if (userService.updateById(user)) {
            User newUser = userService.getById(user.getUserId());
            return JsonResult.ok("修改成功").put("data", newUser);
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 退出
     */
    @PostMapping("/logout")
    public JsonResult logout(User user) {
        if (userService.updateById(user)) {
            return JsonResult.ok("退出成功");
        }
        return JsonResult.error("退出失败");
    }

    /**
     * 修改密码
     **/
    @ResponseBody
    @RequestMapping("/updatePsw")
    public JsonResult updatePsw(Integer userId,String oldPsw, String newPsw) {
        if (StringUtil.isBlank(oldPsw, newPsw)) {
            return JsonResult.error("参数不能为空");
        }
        User user=userService.getById(userId);
        if (!user.getPassword().equals(EndecryptUtil.encrytMd5(oldPsw, 3))) {
            return JsonResult.error("原密码输入不正确");
        }
        user.setUserId(userId);
        user.setPassword(EndecryptUtil.encrytMd5(newPsw, 3));
        if (userService.updateById(user)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }
    
    /**
     * 忘记密码
     **/
    @ResponseBody
    @RequestMapping("/resetPsw")
    public JsonResult resetPsw(String username,String surePsw, String newPsw, String phone) {
        if (StringUtil.isBlank(username, phone, newPsw, surePsw)) {
            return JsonResult.error("参数不能为空");
        }
        if (!surePsw.equals(newPsw)) {
        	return JsonResult.error("两次的密码不一致");
        }
        User user = userService.getByUsername(username);
        if(user ==null){
            return JsonResult.error("账户有误，请修改后提交");
        }
        if(!user.getPhone().equals(phone)) {
        	return JsonResult.error("手机号码有误，请修改后提交");
        }
        user.setPassword(EndecryptUtil.encrytMd5(newPsw, 3));
        if (userService.updateById(user)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }
}

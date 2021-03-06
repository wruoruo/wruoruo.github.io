package com.zsj.es.system.controller;

import com.zsj.es.common.BaseController;
import com.zsj.es.common.JsonResult;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.common.exception.BusinessException;
import com.zsj.es.common.shiro.EndecryptUtil;
import com.zsj.es.common.utils.PoiUtil;
import com.zsj.es.common.utils.StringUtil;
import com.zsj.es.system.model.Role;
import com.zsj.es.system.model.User;
import com.zsj.es.system.service.RoleService;
import com.zsj.es.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
    private static final String DEFAULT_PSW = "123456";  // 用户默认密码
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequiresPermissions("user:view")
    @RequestMapping
    public String user(Model model) {
        List<Role> roles = roleService.list();
        model.addAttribute("roles", roles);
        return "system/user.html";
    }

    /**
     * 查询用户列表
     */
    @RequiresPermissions("user:view")
    @ResponseBody
    @RequestMapping("/list")
    public PageResult<User> list(HttpServletRequest request) {
        return userService.listUser(new PageParam(request).setDefaultOrder(null, new String[]{"create_time"}));
    }

    /**
     * 查询用户列表-通讯好友用
     */
    @RequiresPermissions("user:view")
    @ResponseBody
    @RequestMapping("/listByIm")
    public JsonResult listByIm(HttpServletRequest request){
        List<User> userList=userService.list();
        Map<String,Object> result=new HashMap<>();
        List<Map<String,Object>> li=new ArrayList<>();
        List<Map<String,Object>> li2=new ArrayList<>();
        for(User u:userList){
            if(u.getUsername().equals("admin")){
                Map<String,Object> map=new HashMap<>();
                map.put("id",u.getUserId());
                map.put("username","客服中心");
                map.put("status","online");
                map.put("avatar",u.getAvatar());
                result.put("mine",map);
            }else{
                Map<String,Object> map2=new HashMap<>();
                map2.put("id",u.getUserId());
                map2.put("username",u.getNickName());
                map2.put("avatar",u.getAvatar());
                li.add(map2);
            }
        }
        Map<String,Object> map4=new HashMap<>();
        map4.put("groupname","客户中心");
        map4.put("id",0);
        map4.put("list",li);
        li2.add(map4);

        result.put("friend",li2);
        return JsonResult.ok(0,"查询成功").put("data", result);
    }
    /**
     * 添加用户
     **/
    @RequiresPermissions("user:update")
    @ResponseBody
    @RequestMapping("/add")
    public JsonResult add(User user, String roleIds) {
        user.setState(0);
        user.setUserType(0);
        user.setPassword(EndecryptUtil.encrytMd5(DEFAULT_PSW, 3));
        if (userService.addUser(user, getRoleIds(roleIds))) {
            return JsonResult.ok("添加成功，初始密码为" + DEFAULT_PSW);
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 修改用户
     **/
    @RequiresPermissions("user:update")
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(User user, String roleIds) {
        if (userService.updateUser(user, getRoleIds(roleIds))) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 用逗号分割角色
     */
    private List<Integer> getRoleIds(String rolesStr) {
        List<Integer> roleIds = new ArrayList<>();
        if (rolesStr != null) {
            String[] split = rolesStr.split(",");
            for (String t : split) {
                try {
                    roleIds.add(Integer.parseInt(t));
                } catch (Exception e) {
                }
            }
        }
        return roleIds;
    }

    /**
     * 修改用户状态
     **/
    @RequiresPermissions("user:update")
    @ResponseBody
    @RequestMapping("/updateState")
    public JsonResult updateState(Integer userId, Integer state) {
        if (userId == null) {
            return JsonResult.error("参数userId不能为空");
        }
        if (state == null || (state != 0 && state != 1)) {
            return JsonResult.error("状态值不正确");
        }
        User user = new User();
        user.setUserId(userId);
        user.setState(state);
        if (userService.updateById(user)) {
            return JsonResult.ok();
        }
        return JsonResult.error();
    }

    /**
     * 删除用户
     **/
    @RequiresPermissions("user:update")
    @ResponseBody
    @RequestMapping("/delete")
    public JsonResult delete(Integer userId) {
        if (userId == null) {
            return JsonResult.error("参数userId不能为空");
        }
        if (userService.removeById(userId)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    /**
     * 重置密码
     **/
    @RequiresPermissions("user:update")
    @ResponseBody
    @RequestMapping("/restPsw")
    public JsonResult resetPsw(Integer userId) {
        if (userId == null) {
            return JsonResult.error("参数userId不能为空");
        }
        User user = new User();
        user.setUserId(userId);
        user.setPassword(EndecryptUtil.encrytMd5(DEFAULT_PSW, 3));
        if (userService.updateById(user)) {
            return JsonResult.ok("重置成功，初始密码为" + DEFAULT_PSW);
        }
        return JsonResult.error("重置失败");
    }

    /**
     * 修改自己密码
     **/
    @ResponseBody
    @RequestMapping("/updatePsw")
    public JsonResult updatePsw(String oldPsw, String newPsw) {
        if (StringUtil.isBlank(oldPsw, newPsw)) {
            return JsonResult.error("参数不能为空");
        }
        if (getLoginUser() == null) {
            return JsonResult.error("未登录");
        }
        if (!getLoginUser().getPassword().equals(EndecryptUtil.encrytMd5(oldPsw, 3))) {
            return JsonResult.error("原密码输入不正确");
        }
        User user = new User();
        user.setUserId(getLoginUser().getUserId());
        user.setPassword(EndecryptUtil.encrytMd5(newPsw, 3));
        if (userService.updateById(user)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 导入用户
     */
    @Transactional(rollbackFor = Exception.class)
    @RequiresPermissions("user:update")
    @ResponseBody
    @RequestMapping("/import")
    public JsonResult importData(MultipartFile file) throws IOException {
        List<Role> roleList = roleService.list();
        List<String[]> list = PoiUtil.importData(file.getInputStream(), 1, 0);
        for (int i = 0; i < list.size(); i++) {
            String[] strs = list.get(i);
            User user = new User();
            user.setPassword(EndecryptUtil.encrytMd5("123456", 3));
            user.setUsername(strs[0]);
            user.setNickName(strs[1]);
            user.setSex(strs[2]);
            // 获取角色id
            String[] split = strs[3].split(",");
            List<Integer> roleIds = new ArrayList<>();
            for (String roleName : split) {
                Role role = getRoleByName(roleList, roleName);
                if (role == null) {
                    throw new BusinessException("第" + (i + 2) + "行角色" + roleName + "不存在");
                }
                roleIds.add(role.getRoleId());
            }
            // 添加
            userService.addUser(user, roleIds);
        }
        return JsonResult.ok("导入成功");
    }

    private Role getRoleByName(List<Role> roleList, String roleName) {
        for (Role role : roleList) {
            if (role.getRoleName().equals(roleName)) {
                return role;
            }
        }
        return null;
    }
}

package com.zsj.es.common.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsj.es.common.utils.StringUtil;
import com.zsj.es.system.model.Authorities;
import com.zsj.es.system.model.User;
import com.zsj.es.system.model.UserRole;
import com.zsj.es.system.service.AuthoritiesService;
import com.zsj.es.system.service.UserRoleService;
import com.zsj.es.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Shiro认证和授权
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    private UserService userService;
    @Autowired
    @Lazy
    private UserRoleService userRoleService;
    @Autowired
    @Lazy
    private AuthoritiesService authoritiesService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 角色
        List<UserRole> userRoles = userRoleService.list(new QueryWrapper<UserRole>().eq("user_id", user.getUserId()));
        Set<String> roles = new HashSet<>();
        for (int i = 0; i < userRoles.size(); i++) {
            roles.add(String.valueOf(userRoles.get(i).getRoleId()));
        }
        authorizationInfo.setRoles(roles);
        // 权限
        List<Authorities> authorities = authoritiesService.listByUserId(user.getUserId());
        Set<String> permissions = new HashSet<>();
        for (int i = 0; i < authorities.size(); i++) {
            String authority = authorities.get(i).getAuthority();
            if (StringUtil.isNotBlank(authority)) {
                permissions.add(authority);
            }
        }
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.getByUsername(username);
        if (user == null) {
            throw new UnknownAccountException(); // 账号不存在
        }
        if (user.getState() != 0) {
            throw new LockedAccountException();  // 账号被锁定
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(EndecryptUtil.DEFAULT_SALT), getName());
        return authenticationInfo;
    }
}

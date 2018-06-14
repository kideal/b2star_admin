package com.mall.config.shiro;

import com.mall.entity.BackgroundUser;
import com.mall.entity.Permission;
import com.mall.entity.Role;
import com.mall.service.IBackgroundService;
import com.mall.service.IRoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by huangtao on 2018/6/13
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IBackgroundService backgroundService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        /*查询用户*/
        BackgroundUser user = backgroundService.getBackgroundUserByUserName(username);
        if (null == user) {
            return null;
        }
        /*权限*/
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Role role = roleService.getRoleByRoleId(user.getRoleId());
        authorizationInfo.addRole(role.getName());
        roleService.getRolePermissionByRoleId(user.getRoleId()).forEach(s -> {
            Permission permission = roleService.getPermissionByPermissionId(s.getPermissionId());
            authorizationInfo.addStringPermission(permission.getValue());
        });
        return authorizationInfo;
    }

    /**
     * 身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        /*查询用户*/
        BackgroundUser userInfo = backgroundService.getBackgroundUserByUserName(username);
        if (null == userInfo) {
            return null;
        }
        // 1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
        Object principal = username;
        // 2). credentials: 加密后的密码.
        Object credentials = userInfo.getPassword();
        // 3). realmName: 当前 realm 对象的唯一名字. 调用父类的 getName() 方法
        String realmName = getName();
        // 4). credentialsSalt: 盐值. 注意类型是ByteSource
        //  ByteSource credentialsSalt = ByteSource.Util.bytes(userInfo.getSalt());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);
        return info;
    }
}

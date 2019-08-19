package com.cskaoyan.mall.realm;

import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.bean.lxs.lxsAdminTwo;
import com.cskaoyan.mall.service.lxs.AdminService;
import com.cskaoyan.mall.service.lxt.PermissionService;
import com.cskaoyan.mall.service.lxt.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
     AdminService adminService;
    @Autowired
    RoleService roleService;
    @Autowired
     PermissionService permissionService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();//得到用户名信息
        lxsAdminTwo admin = adminService.selectByName(principal);
        String passwordFromDb = admin.getPassword();//获得数据库中的正确密码
        //第一个参数是身份信息，在授权使用，第二个参数为验证的正确通过信息，第三个为当前realm的名字
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(admin, passwordFromDb, this.getName());
        return simpleAuthenticationInfo;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        lxsAdminTwo admin = (lxsAdminTwo)principalCollection.getPrimaryPrincipal();
        //查询授权信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //授权信息返回
        List<Role> roles = roleService.getRoleByIds(admin.getRoleIds());
        for (Role role : roles) {
            simpleAuthorizationInfo.addRole(role.getName());
            simpleAuthorizationInfo.addStringPermissions(permissionService.getPermissionsByRoleId(role.getId()));
        }

        return simpleAuthorizationInfo;
    }

    public void clearCache(){
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

}

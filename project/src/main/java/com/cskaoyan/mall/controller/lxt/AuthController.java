package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.lxs.lxsAdminTwo;
import com.cskaoyan.mall.mapper.PermissionMapper;
import com.cskaoyan.mall.realm.CustomRealm;
import com.cskaoyan.mall.service.lxs.AdminService;
import com.cskaoyan.mall.service.lxs.impl.AdminServiceImpl;
import com.cskaoyan.mall.service.lxt.PermissionService;
import com.cskaoyan.mall.service.lxt.RoleService;
import com.cskaoyan.mall.service.lxt.impl.PermissionServiceImpl;
import com.cskaoyan.mall.service.lxt.impl.RoleServiceImpl;
import com.cskaoyan.mall.util.Md5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.mgt.SecurityManager;
import sun.security.krb5.Realm;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("admin/auth")
public class AuthController {
    @Autowired
    AdminService adminService;
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    PermissionMapper permissionMapper;
    @RequestMapping("login")
    public BaseRespModel login(@RequestBody lxsAdminTwo admin, HttpServletRequest request){
        BaseRespModel resp = new BaseRespModel<>();
        Subject subject = SecurityUtils.getSubject();
        admin.setPassword(Md5Utils.Md5Again(admin.getPassword()));
        UsernamePasswordToken token = new MallToken(admin.getUsername(),admin.getPassword(),"admin");
        try{
            subject.login(token);
            resp.setErrno(0);
            resp.setData(subject.getSession().getId());
            resp.setErrmsg("成功");
            admin= (lxsAdminTwo) subject.getPrincipal();
            InetAddress localHost = InetAddress.getLocalHost();
            String address = localHost.getHostAddress();
            admin.setLastLoginIp(address);
            admin.setLastLoginTime(new Date());
            adminService.update(admin);
        }catch (AuthenticationException e){
            resp.setErrno(605);
            resp.setErrmsg("用户名或密码错误");
        } catch (UnknownHostException e) {
            resp.setErrno(404);
            resp.setErrmsg("未知的主机地址");
        }
        return resp;
    }

    @RequestMapping("info")
    public BaseRespModel<HashMap> info(String token){
        BaseRespModel<HashMap> resp = new BaseRespModel<>();
        try {
            Subject subject = SecurityUtils.getSubject();
            lxsAdminTwo admin = (lxsAdminTwo) subject.getPrincipal();
            List<Role> Allroles = roleService.getAllRoles();
            List<Permission> Allpermissions = permissionService.getAllPermissions();

            HashMap data = new HashMap();
            List<String> roles = new ArrayList<>();
            for (Role role : Allroles) {
                if(subject.hasRole(role.getName())){
                    roles.add(role.getName());
                }
            }
            data.put("roles", roles);
            List<String> perms = new ArrayList<>();
            if(subject.isPermitted("*")){
                perms.add("*");
            }else{
                for (Permission permission : Allpermissions) {
                    if(permission.getPermission().equals("*")){
                        continue;
                    }
                    if(subject.isPermitted(permissionMapper.getApi(permission.getPermission()))){
                        perms.add(permissionMapper.getApi(permission.getPermission()));
                    }
                }
            }
            data.put("perms", perms);

            data.put("name", admin.getUsername());
            data.put("avatar", admin.getAvatar());
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
       return resp;
    }

    @RequestMapping("logout")
    public BaseRespModel logout(){
        BaseRespModel<HashMap> resp = new BaseRespModel<>();
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.logout();
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }

        return resp;
    }

//    @Autowired
//    CustomRealm realm;
//    @RequestMapping("clearCache")
//    public String clear(){
//        realm.clearCache();
//        return "";
//    }

}

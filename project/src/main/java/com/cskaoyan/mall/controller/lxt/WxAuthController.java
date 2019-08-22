package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.MallToken;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.lxs.lxsAdminTwo;
import com.cskaoyan.mall.service.lxs.AdminService;
import com.cskaoyan.mall.service.lxt.PermissionService;
import com.cskaoyan.mall.service.lxt.RoleService;
import com.cskaoyan.mall.service.lxt.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("wx/auth")
public class WxAuthController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;
    @RequestMapping("login")
    public BaseRespModel login(@RequestBody User user){
        BaseRespModel resp = new BaseRespModel<>();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new MallToken(user.getUsername(),user.getPassword(),"wx");
        try{
            subject.login(token);
            resp.setErrno(0);
            HashMap data = new HashMap();
            resp.setErrmsg("成功");
            user= (User) subject.getPrincipal();
            InetAddress localHost = InetAddress.getLocalHost();
            String address = localHost.getHostAddress();
            user.setLastLoginIp(address);
            user.setLastLoginTime(new Date());
            userService.update(user);
            data.put("token",subject.getSession().getId());
            long time = new Date().getTime()+180000;
            data.put("tokenExpire",new Date(time));
            HashMap userInfo = new HashMap();
            userInfo.put("nickName",user.getNickname());
            userInfo.put("avatarUrl",user.getAvatar());
            data.put("userInfo",userInfo);
            resp.setData(data);
        }catch (AuthenticationException e){
            resp.setErrno(605);
            resp.setErrmsg("用户名或密码错误");
        } catch (UnknownHostException e) {
            resp.setErrno(404);
            resp.setErrmsg("未知的主机地址");
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

}

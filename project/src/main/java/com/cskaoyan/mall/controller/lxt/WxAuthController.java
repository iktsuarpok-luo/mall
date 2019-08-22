package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;

import com.cskaoyan.mall.bean.Register;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.config.MessageApi;
import com.cskaoyan.mall.service.lxt.UserService;
import com.cskaoyan.mall.util.Md5Utils;
import org.apache.shiro.SecurityUtils;
import com.cskaoyan.mall.bean.MallToken;
import com.cskaoyan.mall.service.lxt.PermissionService;
import com.cskaoyan.mall.service.lxt.RoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.net.UnknownHostException;

@RequestMapping("wx/auth")
@RestController
public class WxAuthController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;
    @RequestMapping("regCaptcha")
    public BaseRespModel sendMsg(HttpServletRequest request,@RequestBody User user){
        BaseRespModel resp = new BaseRespModel();
        try {
//            String code = String.valueOf((int)((Math.random()*9+1)*1000));
            String code = "1234";
            Subject subject = SecurityUtils.getSubject();
            subject.getSession().setAttribute("code",code);
            code = "{code:"+code+"}";
            if(!MessageApi.sendMsg(user.getMobile(),code)){
                throw new Exception("发送验证码失败");
            }
            HashMap data = new HashMap();
            data.put("token",subject.getSession().getId());
            resp.setData(data);
            resp.setErrmsg("成功");
            resp.setErrno(0);
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg(e.getMessage());
        }
        return resp;
    }
    @RequestMapping("register")
    public BaseRespModel register(HttpServletRequest request,@RequestBody Register register){
        String code = register.getCode();
        User user = new User();
        user.setUsername(register.getUsername());
        user.setPassword(Md5Utils.Md5Again(register.getPassword()));
        user.setWeixinOpenid(register.getWxCode());
        user.setMobile(register.getMobile());
        BaseRespModel resp = new BaseRespModel();
        try {
            Subject subject = SecurityUtils.getSubject();
            String checkCode = (String) subject.getSession().getAttribute("code");
            if(!checkCode.equals(code)){
                throw new Exception("验证码错误");
            }
            if(userService.getUserByMobile(user.getMobile())!=null){
                throw new Exception("该手机号已经被注册");
            }
            Date date = new Date();
            user.setAddTime(date);
            user.setUpdateTime(date);
            user.setUserLevel((byte) 0);
            user.setNickname(user.getUsername());
            user.setGender((byte) 0);
            InetAddress localHost = InetAddress.getLocalHost();
            String address = localHost.getHostAddress();
            user.setLastLoginIp(address);
            user.setLastLoginTime(date);
            user.setDeleted(false);
            user.setAvatar("");
            user.setBirthday(date);
            user.setStatus((byte) 0);
            userService.addUser(user);
            HashMap data = new HashMap();
            data.put("token",subject.getSession().getId());
            long time = new Date().getTime()+180000;
            data.put("tokenExpire",new Date(time));
            HashMap userInfo = new HashMap();
            userInfo.put("nickName",user.getNickname());
            userInfo.put("avatarUrl",user.getAvatar());
            data.put("userInfo",userInfo);
            resp.setData(data);
            MallToken token= new MallToken(user.getUsername(),user.getPassword(),"wx");
            subject.login(token);
            resp.setErrmsg("成功");
            resp.setErrno(0);
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg(e.getMessage());
        }
        return resp;
    }

    @RequestMapping("login")
    public BaseRespModel login(@RequestBody User user){
        BaseRespModel resp = new BaseRespModel<>();
        Subject subject = SecurityUtils.getSubject();
        user.setPassword(Md5Utils.Md5Again(user.getPassword()));
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
            long time = new Date().getTime()+18000000;
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
    @RequestMapping("reset")
    public BaseRespModel reset(@RequestBody Register register){
        String code = register.getCode();
        BaseRespModel resp = new BaseRespModel();
        try{
            Subject subject = SecurityUtils.getSubject();
            String checkCode = (String) subject.getSession().getAttribute("code");
            if(!checkCode.equals(code)){
                throw new Exception("验证码错误");
            }
            User user = userService.getUserByMobile(register.getMobile());
            if(user==null){
                throw new Exception("该手机号尚未注册");
            }
            user.setPassword(Md5Utils.Md5Again(register.getPassword()));
            Date date = new Date();
            user.setUpdateTime(date);
            InetAddress localHost = InetAddress.getLocalHost();
            String address = localHost.getHostAddress();
            user.setLastLoginIp(address);
            user.setLastLoginTime(date);
            userService.update(user);
            resp.setErrmsg("成功");
            resp.setErrno(0);
        }catch (Exception e){
            resp.setErrmsg(e.getMessage());
            resp.setErrno(1);
        }
        return resp;

    }
}

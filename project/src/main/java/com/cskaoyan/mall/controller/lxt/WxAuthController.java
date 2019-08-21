package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Register;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.config.MessageApi;
import com.cskaoyan.mall.service.lxt.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;

@RequestMapping("wx/auth")
@RestController
public class WxAuthController {
    @Autowired
    UserService userService;
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
        user.setPassword(register.getPassword());
        user.setWeixinOpenid(register.getWxCode());
        user.setMobile(register.getMobile());
        BaseRespModel resp = new BaseRespModel();
        try {
            Subject subject = SecurityUtils.getSubject();
            String checkCode = (String) subject.getSession().getAttribute("code");
            if(!checkCode.equals(code)){
                throw new Exception("验证码错误");
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
            resp.setErrmsg("成功");
            resp.setErrno(0);
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg(e.getMessage());
        }
        return resp;
    }
}

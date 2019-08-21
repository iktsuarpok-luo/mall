package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.lxs.lxsAdminTwo;
import com.cskaoyan.mall.service.lxs.AdminService;
import com.cskaoyan.mall.util.Md5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("admin/profile")
public class ProfileController {
    @Autowired
    AdminService adminService;
    @RequestMapping("password")
    public BaseRespModel passwordChange(String oldPassword,String newPassword){
        BaseRespModel resp = new BaseRespModel();
        oldPassword = Md5Utils.getMd5(oldPassword);
        newPassword = Md5Utils.getMd5(newPassword);
        try {
            Subject subject = SecurityUtils.getSubject();
            lxsAdminTwo admin = (lxsAdminTwo) subject.getPrincipal();
            admin = adminService.selectByName(admin.getUsername());
            String password = admin.getPassword();
            if (!password.equals(oldPassword)){
                throw new Exception("密码错误");
            }
            admin.setPassword(newPassword);
            admin.setUpdateTime(new Date());
            adminService.update(admin);
            resp.setErrno(0);
            resp.setErrmsg("成功");
            resp.setData(admin);
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg(e.getMessage());
        }
        return resp;
    }
}

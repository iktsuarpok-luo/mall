package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("admin/auth")
public class AuthController {
    @RequestMapping("login")
    public BaseRespModel<String> login(String username, String password){
        BaseRespModel<String> resp = new BaseRespModel<>();
        resp.setErrno(0);
        resp.setData("48a3c6d1-4fb7-44f8-afa1-ffb8a6058001");
        resp.setErrmsg("成功");
        return resp;
    }
    @RequestMapping("info")
    public BaseRespModel<HashMap> info(){
        BaseRespModel<HashMap> resp = new BaseRespModel<>();
        resp.setErrno(0);
        resp.setErrmsg("成功");
        HashMap data = new HashMap();
        String[] roles = new String[]{"超级管理员"};
        data.put("roles",roles);
        data.put("name","admin123");
        String[] perms = new String[]{"*"};
        data.put("perms",perms);
        data.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        resp.setData(data);
       return resp;
    }

}

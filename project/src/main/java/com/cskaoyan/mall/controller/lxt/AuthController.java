package com.cskaoyan.mall.controller.lxt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class AuthController {
    @RequestMapping("auth/login")
    @ResponseBody
    @CrossOrigin("http://localhost:9528")
    public HashMap login(String username, String password){
        HashMap map = new HashMap();
        map.put("errno",0);
        map.put("data","4139bb1c-b077-4bff-a69c-a0cc39eecdb3");
        map.put("errmsg","成功");
        return map;
    }
    @RequestMapping("auth/info")
    @ResponseBody
    @CrossOrigin("http://localhost:9528")
    public HashMap info(){
        HashMap map = new HashMap();
        map.put("errno",0);
        HashMap data = new HashMap();
        String[] roles = new String[]{"超级管理员"};
        data.put("roles",roles);
        data.put("name","admin123");
        String[] perms = new String[]{"*"};
        data.put("perms",perms);
        data.put("avater","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("data",data);
        map.put("errmsg","成功");
       return map;
    }

}

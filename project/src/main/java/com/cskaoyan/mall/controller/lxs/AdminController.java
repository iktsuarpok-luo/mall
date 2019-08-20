package com.cskaoyan.mall.controller.lxs;

import com.cskaoyan.mall.bean.lxs.lxsAdmin;
import com.cskaoyan.mall.bean.lxs.lxsAdminTwo;
import com.cskaoyan.mall.bean.lxs.lxsRole;
import com.cskaoyan.mall.service.lxs.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping("admin/list")
    public Map getList(String username){
        if(username==null) {
            List<lxsAdmin> list = adminService.getdata();
            Map map1 = new HashMap();
            Map map2 = new HashMap();
            map1.put("data", map2);
            map1.put("errmsg", "成功");
            map1.put("errno", 0);
            map2.put("items", list);
            return map1;
        }
        List<lxsAdmin> list = adminService.searchByName(username);
        Map map1 = new HashMap();
        Map map2 = new HashMap();
        map1.put("data", map2);
        map1.put("errmsg", "成功");
        map1.put("errno", 0);
        map2.put("items", list);
        return map1;
    }

    @RequestMapping("role/options")
    public Map getOption(){
        List<lxsRole> list=adminService.getOptionData();
        HashMap map = new HashMap();
        map.put("data",list);
        map.put("errmsg","成功");
        map.put("errno",0);
        return map;
    }

    @PostMapping("admin/delete")
    public Map delete(@RequestBody lxsAdmin lxsAdmin){
        adminService.deleteById(lxsAdmin.getId());
        HashMap map = new HashMap();
        map.put("errmsg","成功");
        map.put("errno",0);
        return map;
    }
    @PostMapping("admin/create")
    public Map insert(@RequestBody lxsAdminTwo admin)  {
        int i = adminService.usernameExist(admin.getUsername());
        if(i==1){
            HashMap map = new HashMap();
            map.put("errmsg","该用户已经存在");
            map.put("errno",602);
            return map;
        }
        adminService.insert(admin);
        lxsAdminTwo aa=adminService.selectByName(admin.getUsername());
        Map map = new HashMap();
        map.put("data",aa);
        map.put("errmsg","成功");
        map.put("errno",0);
        return map;
    }
    @PostMapping("admin/update")
    public Map update(@RequestBody lxsAdminTwo admin){
        int i=adminService.update(admin);
        if(i==1){
            HashMap map = new HashMap();
            map.put("errmsg","该用户已经存在");
            map.put("errno",602);
            return map;
        }
        lxsAdminTwo aa=adminService.selectById(admin.getId());
        Map map = new HashMap();
        map.put("data",aa);
        map.put("errmsg","成功");
        map.put("errno",0);
        return map;
    }
}

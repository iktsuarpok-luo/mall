package com.cskaoyan.mall.controller.lxs;


import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.bean.lxs.Impower;
import com.cskaoyan.mall.bean.lxs.data.*;
import com.cskaoyan.mall.bean.lxs.lxsAdmin;
import com.cskaoyan.mall.bean.lxs.lxsAdminTwo;
import com.cskaoyan.mall.bean.lxs.lxsRole;
import com.cskaoyan.mall.mapper.PermissionMapper;
import com.cskaoyan.mall.service.lxs.AdminService;
import com.cskaoyan.mall.util.AnnotationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Component
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    PermissionMapper permissionMapper;
    @RequestMapping("admin/list")
    public Map getList(String username,int page,int limit){
        if(username==null) {
            List<lxsAdmin> list = adminService.getdata();
            Map map1 = new HashMap();
            Map map2 = new HashMap();
            map1.put("data", map2);
            map1.put("errmsg", "成功");
            map1.put("errno", 0);
            map2.put("items", list.subList(limit*(page-1),Math.min(limit*page,list.size())));
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
    @AnnotationLog("删除用户")
    public Map delete(@RequestBody lxsAdmin lxsAdmin){
        adminService.deleteById(lxsAdmin.getId());
        HashMap map = new HashMap();
        map.put("errmsg","成功");
        map.put("errno",0);
        return map;
    }
    @PostMapping("admin/create")
    @AnnotationLog("增加用户")
    public Map insert(@RequestBody lxsAdminTwo admin){
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
    @AnnotationLog("编辑用户")
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

    @RequestMapping("log/list")
    public Map log(int page,int limit){
        HashMap map1 = new HashMap();
        HashMap map2 = new HashMap();
        List<com.cskaoyan.mall.bean.Log> logList=adminService.getLog();
        map2.put("items",logList.subList(limit*(page-1),Math.min(limit*page,logList.size())));
        map2.put("total",logList.size());
        map1.put("data",map2);
        map1.put("errmsg","成功");
        map1.put("errno",0);
        return map1;
    }
 /*  这里是插入数据的代码
    @PostMapping("admin/lixishuang")
    public String data(@RequestBody datalxs<dataone<datatwo<datathree<datafour>>>> datalxs){
        System.out.println(datalxs);
        datafour insert = new datafour();
        for (datatwo<datathree<datafour>> datatwo : datalxs.getData().getSystemPermissions()) {
            insert.setLabel(datatwo.getLabel());
            insert.setId(datatwo.getId());
            insert.setApi("");
            insert.setPid("0");
            permissionMapper.insertdata(insert);
            for (datathree<datafour> child : datatwo.getChildren()) {
                insert.setLabel(child.getLabel());
                insert.setApi("");
                insert.setId(child.getId());
                insert.setPid(datatwo.getId());
                permissionMapper.insertdata(insert);
                for (datafour datafour : child.getChildren()) {
                    datafour.setPid(child.getId());
                    permissionMapper.insertdata(datafour);
                }
            }
        }
            return "你好啊";
    }*/

    @RequestMapping("role/update")
    @AnnotationLog("编辑角色")
    public Map update(@RequestBody Role role){
        int i=adminService.roleUpdate(role);
        HashMap map = new HashMap();
        if(i==1){
            map.put("errmsg","角色名重复");
            map.put("errno",502);
            return map;
        }
        map.put("errmsg","成功");
        map.put("errno",0);
        return map;
    }
    @RequestMapping("role/create")
    @AnnotationLog("添加角色")
    public Map insert(@RequestBody Role role){
        role.setAddTime(new Date());
        role.setUpdateTime(new Date());
        role.setDeleted(false);
        int i = adminService.roleInsert(role);
        HashMap map = new HashMap();
        if(i==0){
            Role role2=adminService.selectByRoleName(role.getName());
            map.put("data",role2);
            map.put("errmsg","成功");
            map.put("errno",0);
            return map;
        }
        map.put("errmsg","角色已经存在");
        map.put("errno",640);
        return map;

    }
    @RequestMapping("role/delete")
    @AnnotationLog("删除角色")
    public Map roledelete(@RequestBody Role role){
        int i=adminService.deleteRole(role);
        HashMap map = new HashMap();
        if(i==0){
            map.put("errmsg","成功");
            map.put("errno",0);
            return map;
        }
        map.put("errmsg","当前角色存在管理员，不能删除");
        map.put("errno",642);
        return map;
    }
    @RequestMapping("role/list")
    public Map selectRoleList(String name,int page,int limit) {
        if (name != null) {
            List<Role> list = adminService.selectByRoleNames(name);
            HashMap map1 = new HashMap();
            HashMap map2 = new HashMap();

            if (list.size() != 0) {
                map2.put("items", list.subList(limit*(page-1),Math.min(limit*page,list.size())));
                map2.put("total", 1);
                map1.put("data", map2);
                map1.put("errmsg", "成功");
                map1.put("errno", 0);
                return map1;
            }
            map1.put("errmsg", "当前角色存在管理员，不能删除");
            map1.put("errno", 642);
            return map1;
        }

        HashMap map1= new HashMap();
        HashMap map2 = new HashMap();
        List<Role> list=adminService.getAllRoleList();
        map2.put("items",list);
        map2.put("total",list.size());
        map1.put("data",map2);
        map1.put("errmsg","成功");
        map1.put("errno",0);
        return map1;
    }
    @GetMapping("role/permissions")
    public BaseRespModel permission(int roleId){
        BaseRespModel resp = new BaseRespModel();
        String[] assignedPermissions =adminService.selectPermission(roleId);
        datatwo<datathree<datafour>[]>[] systemPermissions = adminService.selectAllSP();
        try{
            HashMap data = new HashMap();
            data.put("assignedPermissions",assignedPermissions);
            data.put("systemPermissions",systemPermissions);
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrmsg("失败");
            resp.setErrno(1);
        }
        return resp;
    }
    @PostMapping("role/permissions")
    public Map impower(@RequestBody Impower impower){
        HashMap map = new HashMap();
        int roleId = impower.getRoleId();
        if(roleId==1){

            map.put("errmsg","当前角色的超级权限不能变更");
            map.put("errno",641);
            return map;
        }
        adminService.insertPower(impower);

        map.put("errmsg","成功");
        map.put("errno",0);
        return map;
    }


}

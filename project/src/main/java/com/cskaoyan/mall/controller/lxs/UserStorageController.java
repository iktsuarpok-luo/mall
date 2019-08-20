package com.cskaoyan.mall.controller.lxs;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.service.lxs.UserStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("admin/storage")
public class UserStorageController {
    @Autowired
    UserStorageService userStorageService;
    @RequestMapping("list")
    public BaseRespModel show(int page,int limit,String sort,String order,String name,String key){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            List<Storage> items = userStorageService.getStorageList(page,limit,sort,order,name,key);
            long total = userStorageService.countStorage();
            data.put("items",items);
            data.put("total",total);
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
    /*@RequestMapping("create")
    public BaseRespModel create(@RequestBody Storage storage){
        BaseRespModel resp = new BaseRespModel();
        try{
            storage.setDeleted(false);
            Date date = new Date();
            storage.setAddTime(date);
            storage.setUpdateTime(date);
            storage.setSortOrder((byte) 100);
            storage = userStorageService.create(storage);
            resp.setData(storage);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }*/
    @RequestMapping("update")
    public BaseRespModel update(@RequestBody Storage storage){
        BaseRespModel resp = new BaseRespModel();
        try{
            Date date = new Date();
            storage.setUpdateTime(date);
            storage=userStorageService.update(storage);
            resp.setData(storage);
            resp.setErrmsg("成功");
            resp.setErrno(0);
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
    @RequestMapping("delete")
    public BaseRespModel delete(@RequestBody Storage storage){
        BaseRespModel resp = new BaseRespModel();
        try{
            userStorageService.delete(storage);
            resp.setErrmsg("成功");
            resp.setErrno(0);
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
}

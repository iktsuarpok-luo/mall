package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.service.xw.UserAddressService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("wx/address")
@RestController
public class WxAddressController {
    @Autowired
    UserAddressService userAddressService;
    @RequestMapping("list")
    public BaseRespModel list(){
        BaseRespModel resp = new BaseRespModel();
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        try{
            List<Address> list = userAddressService.getAddressListByUser(user.getId());
            resp.setData(list);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
    @RequestMapping("detail")
    public BaseRespModel detail(int id){
        BaseRespModel resp = new BaseRespModel();
        try{
            resp.setData(userAddressService.getAddressById(id));
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return  resp;
    }
    @RequestMapping("save")
    public BaseRespModel save(@RequestBody Address address){
        BaseRespModel resp = new BaseRespModel();
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        try{
            resp.setData(userAddressService.update(address,user.getId()));
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return  resp;
    }
    @RequestMapping("delete")
    public BaseRespModel delete(@RequestBody Address address){
        BaseRespModel resp = new BaseRespModel();
        try {
            userAddressService.delete(address.getId());
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
}

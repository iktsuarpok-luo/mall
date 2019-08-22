package com.cskaoyan.mall.controller.xw;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.PageBean;
import com.cskaoyan.mall.service.xw.UserAddressService;
import com.cskaoyan.mall.service.xw.UserManageService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class UserAddressController {
    @Autowired
    UserAddressService userAddressService;
    @RequestMapping("address/list")
    public BaseRespModel<PageBean> queryAddress(int page, int limit, String userId, String name){
        BaseRespModel<PageBean> respModel = new BaseRespModel<>();
        try{
        List<Address> addressList = userAddressService.queryAddress(page, limit, userId, name);
        PageInfo<Address> pageInfo = new PageInfo<>(addressList);
        PageBean<Address> pageBean = new PageBean<>(addressList, pageInfo.getTotal());
        respModel.setData(pageBean);
        respModel.setErrmsg("成功");
        respModel.setErrno(0);
        } catch (Exception e){
            respModel.setErrno(1);
            respModel.setErrmsg("失败");
        }
        return respModel;
    }
}

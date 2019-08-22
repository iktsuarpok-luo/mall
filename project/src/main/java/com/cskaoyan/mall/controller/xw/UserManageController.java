package com.cskaoyan.mall.controller.xw;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.PageBean;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.service.xw.UserManageService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//表示以json形式返回的controller
@RestController
//窄化请求
@RequestMapping("admin")
public class UserManageController {
    @Autowired
    UserManageService userManageService;
    @RequestMapping("user/list")
    public BaseRespModel<PageBean> queryAllUser(int page,int limit,String username,String mobile){
        BaseRespModel<PageBean> respModel = new BaseRespModel<>();
        try{
        List<User> userList = userManageService.queryAllUser(page, limit, username, mobile);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        PageBean<User> pageBean = new PageBean<>(userList, pageInfo.getTotal());

        respModel.setData(pageBean);
        respModel.setErrmsg("成功");
        respModel.setErrno(0);}catch (Exception e){
            respModel.setErrno(1);
            respModel.setErrmsg("失败");
        }
        return respModel;
    }
}

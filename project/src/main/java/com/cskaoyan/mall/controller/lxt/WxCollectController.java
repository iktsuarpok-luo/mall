package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.service.xw.UserCollectService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.HashMap;

@RestController
@RequestMapping("wx/collect")
public class WxCollectController {
    @Autowired
    UserCollectService collectService;
    @RequestMapping("list")
    public BaseRespModel list(int type,int page,int size){
        BaseRespModel resp = new BaseRespModel();
        try{
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();
            HashMap data = new HashMap();
            data.put("collectList",collectService.queryCollect(page,size,user.getId(),type));
            data.put("totalPages",Math.ceil(collectService.count(user.getId(),type)/size));
            resp.setData(data);
            resp.setErrmsg("成功");
            resp.setErrno(0);
        }catch (Exception e){
            resp.setErrmsg("失败");
            resp.setErrno(1);
        }
        return resp;
    }
}

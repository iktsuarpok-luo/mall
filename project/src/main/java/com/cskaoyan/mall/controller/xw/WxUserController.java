package com.cskaoyan.mall.controller.xw;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.service.lxt.OrderService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("wx/user")
public class WxUserController {
    @Autowired
    OrderService orderService;
    @RequestMapping("index")
    public BaseRespModel userIndex(){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();
            HashMap order = new HashMap();
            order.put("unpaid",orderService.countUnpaid(user));
            order.put("unship",orderService.countUnship(user));
            order.put("unrecv",orderService.countUnrecv(user));
            order.put("uncomment",orderService.countUncomment(user));
            data.put("order",order);
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrmsg("失败");
            resp.setErrno(1);
        }
        return resp;
    }

}

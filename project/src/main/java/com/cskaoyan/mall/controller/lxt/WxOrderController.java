package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.service.lxt.OrderService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("wx/order")
public class WxOrderController {
    @Autowired
    OrderService orderService;
    @RequestMapping("list")
    public BaseRespModel list(int showType , int page , int size){
        BaseRespModel resp = new BaseRespModel();
        try {
            Subject subject = SecurityUtils.getSubject();
            User user = (User)subject.getPrincipal();
            HashMap map = new HashMap();
            map.put("count",orderService.countByType(showType,user.getId()));
            map.put("totalPages",Math.ceil((long)map.get("count")/size));
            map.put("data",orderService.getOrderListByType(showType,page,size,user.getId()));
            resp.setData(map);
            resp.setErrmsg("成功");
            resp.setErrno(0);
        }catch (Exception e){
            resp.setErrmsg("失败");
            resp.setErrno(1);
        }
        return resp;
    }
}

package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.service.lxt.OrderService;
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
            HashMap map = new HashMap();
            map.put("count",orderService.countByType(showType));
            map.put("totalPages",(int)map.get("count")/size+1);
            map.put("data",orderService.getOrderListByType(showType,page,size));
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

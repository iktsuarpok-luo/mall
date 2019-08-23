package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.RefundBean;
import com.cskaoyan.mall.service.lxt.BrandService;
import com.cskaoyan.mall.service.lxt.OrderService;
import com.cskaoyan.mall.service.lxt.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("admin/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @RequestMapping("list")
    public BaseRespModel show(int page, int limit, String sort, String order,String orderSn,String userId,Short[] orderStatusArray){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            List<Order> items = orderService.getOrderList(page,limit,sort,order,orderSn,userId,orderStatusArray);
            long total = orderService.countOrder();
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
    @RequestMapping("detail")
    public BaseRespModel showDetail(int id){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            data.put("order",orderService.getOrderById(id));
            data.put("orderGoods",orderService.getOrderGoods(id));
            data.put("user",userService.getUserById(((Order)data.get("order")).getUserId()));
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
    @RequestMapping("refund")
    public BaseRespModel refund(@RequestBody RefundBean refundBean){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            Order order = orderService.getOrderById(refundBean.getOrderId());
            if(order.getActualPrice().compareTo(refundBean.getRefundMoney())!=0){
                throw new Exception("退款金额异常");
            }
            order.setOrderStatus((short) 203);
            order.setUpdateTime(new Date());
            orderService.update(order);
            resp.setData(order);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg(e.getMessage());
        }
        return resp;
    }

}
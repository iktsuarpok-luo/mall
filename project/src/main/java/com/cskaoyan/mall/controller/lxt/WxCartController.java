package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.bean.CartTotal;
import com.cskaoyan.mall.bean.Check;
import com.cskaoyan.mall.service.lxt.CartService;
import org.apache.tomcat.util.modeler.BaseModelMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("wx/cart")
public class WxCartController {
    @Autowired
    CartService cartService;
    @RequestMapping("index")
    public BaseRespModel index(){
        BaseRespModel resp = new BaseRespModel();
        try{
            List<Cart> cartList = new ArrayList<>();
            CartTotal cartTotal = new CartTotal();
            cartList = cartService.getCartList();
            cartTotal = cartService.check();
            HashMap data = new HashMap();
            data.put("cartList",cartList);
            data.put("cartTotal",cartTotal);
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
    @RequestMapping("check")
    public BaseRespModel check(@RequestBody Check check){
        BaseRespModel resp = new BaseRespModel();
        try{
            List<Cart> cartList = new ArrayList<>();
            CartTotal cartTotal = new CartTotal();
            cartList = cartService.getCartList();
            cartTotal = cartService.updateCheck(check);
            HashMap data = new HashMap();
            data.put("cartList",cartList);
            data.put("cartTotal",cartTotal);
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
}

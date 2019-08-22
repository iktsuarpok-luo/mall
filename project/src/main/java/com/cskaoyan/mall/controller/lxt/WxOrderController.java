package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.lxt.OrderService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("wx/order")
public class WxOrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrdergoodsMapper ordergoodsMapper;
    @Autowired
    GoodsMapper goodsMapper;
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
    @RequestMapping("prepay")
    public BaseRespModel prepay(@RequestBody Order order){
        BaseRespModel resp = new BaseRespModel();
        resp.setErrmsg("订单不能支付");
        resp.setErrno(724);
        return resp;
    }
    @RequestMapping("submit")
    public BaseRespModel submit(@RequestBody OrderSubmit orderSubmit){
        BaseRespModel resp = new BaseRespModel();
        try{
            List<Cart> list = new ArrayList<>();
            if(orderSubmit.getCartId()==0) {
                CartExample cartExample = new CartExample();
                cartExample.createCriteria().andCheckedEqualTo(true);
                list = cartMapper.selectByExample(cartExample);
            }else{
                list.add(cartMapper.selectByPrimaryKey(orderSubmit.getCartId()));
            }
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();
            Order order = new Order();
            Address address = addressMapper.selectByPrimaryKey(orderSubmit.getAddressId());
            order.setAddress(address.getAddress());
            Date date = new Date();
            order.setAddTime(date);
            order.setUpdateTime(date);
            order.setOrderStatus((short) 101);
            order.setUserId(user.getId());
            order.setMessage(orderSubmit.getMessage());
            order.setDeleted(false);
            order.setConsignee(address.getName());
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            order.setOrderSn(df.format(new Date())+String.valueOf((int)((Math.random()*9+1)*1000)));
            order.setMobile(address.getMobile());
            List<CheckedGood> checkedGoods = new ArrayList<>();
            BigDecimal goodsTotalPrice = BigDecimal.valueOf(0);
            for (Cart cart : list) {
                checkedGoods.add(getCheckedGoodByCart(cart,user));
                goodsTotalPrice=goodsTotalPrice.add(cart.getPrice());
                cartMapper.deleteByPrimaryKey(cart.getId());
            }
            order.setCouponPrice(BigDecimal.valueOf(0));//优惠券优惠
            order.setFreightPrice(BigDecimal.valueOf(3));//运费
            order.setGoodsPrice(goodsTotalPrice);//商品合计
            order.setActualPrice(order.getFreightPrice().add(order.getGoodsPrice()));//实付
            order.setGrouponPrice(BigDecimal.valueOf(6));
            order.setOrderPrice(order.getActualPrice());
            order.setIntegralPrice(BigDecimal.valueOf(order.getActualPrice().intValue()));
            orderMapper.insert(order);
            HashMap data = new HashMap<>();
            data.put("orderId",order.getId());
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrmsg("失败");
            resp.setErrno(1);
        }
        return resp;
    }
    private CheckedGood getCheckedGoodByCart(Cart cart,User user) {
        CheckedGood checkedGood = new CheckedGood();
        Date date = new Date();
        checkedGood.setAddTime(date);
        checkedGood.setUpdatetime(date);
        checkedGood.setChecked(true);
        checkedGood.setDeleted(false);
        checkedGood.setGoodsId(cart.getGoodsId());
        checkedGood.setGoodsName(cart.getGoodsName());
        checkedGood.setGoodsSn(cart.getGoodsSn());
        checkedGood.setId(cart.getId());
        checkedGood.setNumber(cart.getNumber());
        checkedGood.setPicUrl(cart.getPicUrl());
        checkedGood.setPrice(cart.getPrice());
        checkedGood.setProductId(cart.getProductId());
        checkedGood.setSpecifications(cart.getSpecifications());
        checkedGood.setUserId(user.getId());
        return checkedGood;
    }
    @RequestMapping("detail")
    public BaseRespModel detail(int orderId){
        BaseRespModel resp = new BaseRespModel();
        try{
            HashMap data = new HashMap();
            Order order = orderMapper.selectByPrimaryKey(orderId);
            data.put("orderInfo",order);
            OrdergoodsExample ordergoodsExample = new OrdergoodsExample();
            ordergoodsExample.createCriteria().andOrderIdEqualTo(orderId);
            List<Ordergoods> ordergoods = ordergoodsMapper.selectByExample(ordergoodsExample);
            List<Goods> list = new ArrayList<>();
            for (Ordergoods ordergood : ordergoods) {
                list.add(goodsMapper.selectByPrimaryKey(ordergood.getGoodsId()));
            }
            data.put("orderGoods",list);

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

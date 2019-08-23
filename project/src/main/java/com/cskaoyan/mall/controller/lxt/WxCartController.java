package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.FastAdd;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.cskaoyan.mall.mapper.CartMapper;
import com.cskaoyan.mall.mapper.GoodsproductMapper;
import com.cskaoyan.mall.service.lxt.CartService;
import com.cskaoyan.mall.service.zt.GoodsService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("wx/cart")
public class WxCartController {
    @Autowired
    CartService cartService;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsproductMapper goodsproductMapper;
    @Autowired
    AddressMapper addressMapper;
    @RequestMapping("index")
    public BaseRespModel index() {
        BaseRespModel resp = new BaseRespModel();
        try {
            List<Cart> cartList = new ArrayList<>();
            CartTotal cartTotal = new CartTotal();
            cartList = cartService.getCartList();
            cartTotal = cartService.check();
            HashMap data = new HashMap();
            data.put("cartList", cartList);
            data.put("cartTotal", cartTotal);
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        } catch (Exception e) {
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }

    @RequestMapping("checked")
    public BaseRespModel check(@RequestBody Check check) {
        BaseRespModel resp = new BaseRespModel();
        try {
            List<Cart> cartList = new ArrayList<>();
            CartTotal cartTotal = new CartTotal();
            cartTotal = cartService.updateCheck(check);
            cartList = cartService.getCartList();
            HashMap data = new HashMap();
            data.put("cartList", cartList);
            data.put("cartTotal", cartTotal);
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        } catch (Exception e) {
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }

    @RequestMapping("add")
    public BaseRespModel add(@RequestBody AddCart addCart) {
        BaseRespModel resp = new BaseRespModel();
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andProductIdEqualTo(addCart.getProductId());
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        if(carts.size()!=0){
            carts.get(0).setNumber((short)(carts.get(0).getNumber()+addCart.getNumber()));
            cartMapper.updateByPrimaryKey(carts.get(0));
        }else {
            Cart cart = new Cart();
            cart.setChecked(false);
            cart.setDeleted(false);
            Date date = new Date();
            cart.setAddTime(date);
            cart.setUpdateTime(date);
            cart.setNumber((short) addCart.getNumber());
            cart.setProductId(addCart.getProductId());
            Goods goods = goodsService.findGoodsById(addCart.getGoodsId());
            cart.setGoodsId(goods.getId());
            cart.setGoodsName(goods.getName());
            cart.setGoodsSn(goods.getGoodsSn());
            cart.setPicUrl(goods.getPicUrl());
//            cart.setPrice(BigDecimal.valueOf(goods.getRetailPrice().intValue() * addCart.getNumber()));
            Goodsproduct goodsproduct = new Goodsproduct();
            goodsproduct = goodsproductMapper.selectByPrimaryKey(addCart.getProductId());
            cart.setSpecifications(Arrays.toString(goodsproduct.getSpecifications()));
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();
            cart.setUserId(user.getId());
            cartMapper.insert(cart);
        }

        resp.setData(0);
        resp.setErrmsg("成功");
        resp.setErrno(0);

        return resp;
    }

    @RequestMapping("delete")
    public BaseRespModel delete(@RequestBody CheckCart checkCart){
        BaseRespModel resp = new BaseRespModel();
        try{
            List<Integer> productIds = checkCart.getProductIds();
            for (int productId : productIds) {
                CartExample cartExample = new CartExample();
                cartExample.createCriteria().andProductIdEqualTo(productId);
                List<Cart> carts = cartMapper.selectByExample(cartExample);
                for (Cart cart : carts) {
                    cartMapper.deleteByPrimaryKey(cart.getId());
                }
            }
            List<Cart> cartList = new ArrayList<>();
            CartTotal cartTotal = new CartTotal();
            cartList = cartService.getCartList();
            cartTotal = cartService.check();
            HashMap data = new HashMap();
            data.put("cartList", cartList);
            data.put("cartTotal", cartTotal);
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
    @RequestMapping("update")
    public BaseRespModel  update(@RequestBody Cart cart){
        BaseRespModel resp = new BaseRespModel();
        try{
           Cart oldCart = cartMapper.selectByPrimaryKey(cart.getId());
           oldCart.setNumber(cart.getNumber());
           cartMapper.updateByPrimaryKey(cart);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
    @RequestMapping("fastadd")
    public BaseRespModel fastadd(@RequestBody FastAdd fastAdd){
        BaseRespModel resp = new BaseRespModel();
        try {
            Cart cart = new Cart();
            cart.setChecked(false);
            cart.setDeleted(false);
            Date date = new Date();
            cart.setAddTime(date);
            cart.setUpdateTime(date);
            cart.setNumber((short) fastAdd.getNumber());
            cart.setProductId(fastAdd.getProductId());
            Goods goods = goodsService.findGoodsById(fastAdd.getGoodsId());
            cart.setGoodsId(goods.getId());
            cart.setGoodsName(goods.getName());
            cart.setGoodsSn(goods.getGoodsSn());
            cart.setPicUrl(goods.getPicUrl());
//            cart.setPrice(BigDecimal.valueOf(goods.getRetailPrice().intValue() * fastAdd.getNumber()));
            Goodsproduct goodsproduct = new Goodsproduct();
            goodsproduct = goodsproductMapper.selectByPrimaryKey(fastAdd.getProductId());
            cart.setSpecifications(Arrays.toString(goodsproduct.getSpecifications()));
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();
            cart.setUserId(user.getId());
            cartMapper.insert(cart);
            resp.setData(cart.getId());
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
    @RequestMapping("checkout")
    public BaseRespModel checkout(int cartId){
        BaseRespModel resp = new BaseRespModel();
        try{
            List<Cart> list = new ArrayList<>();
            if(cartId==0) {
                CartExample cartExample = new CartExample();
                cartExample.createCriteria().andCheckedEqualTo(true);
                list = cartMapper.selectByExample(cartExample);
            }else{
                list.add(cartMapper.selectByPrimaryKey(cartId));
            }
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();
            CheckOut checkOut = new CheckOut();
            AddressExample addressExample = new AddressExample();
            addressExample.createCriteria().andIsDefaultEqualTo(true).andUserIdEqualTo(user.getId());
            checkOut.setAddressId(addressMapper.selectByExample(addressExample).get(0).getId());
            AddressExample addressExample1 = new AddressExample();
            addressExample1.createCriteria().andUserIdEqualTo(user.getId()).andIdEqualTo(checkOut.getAddressId());
            checkOut.setCheckedAddress(addressMapper.selectByExample(addressExample1).get(0));
            List<CheckedGood> checkedGoods = new ArrayList<>();
            BigDecimal goodsTotalPrice = BigDecimal.valueOf(0);
            for (Cart cart : list) {
                checkedGoods.add(getCheckedGoodByCart(cart,user));
                goodsTotalPrice=goodsTotalPrice.add(cart.getPrice());
            }
            checkOut.setCheckedGoodsList(checkedGoods);
            checkOut.setCouponId(0);
            checkOut.setAvailableCouponLength(0);
            checkOut.setCouponPrice(0);//优惠券优惠
            checkOut.setFreightPrice(BigDecimal.valueOf(3));//运费
            checkOut.setGoodsTotalPrice(goodsTotalPrice);//商品合计
            checkOut.setActualPrice(checkOut.getFreightPrice().add(checkOut.getGoodsTotalPrice()));//实付
            checkOut.setGrouponPrice(6);
            checkOut.setOrderTotalPrice(checkOut.getActualPrice().intValue());
            checkOut.setGrouponRulesId(8);
            resp.setData(checkOut);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrmsg("失败");
            resp.setErrno(1);
        }
        return resp;
    }
    @RequestMapping("goodscount")
    public  BaseRespModel goodscount(){
        BaseRespModel resp = new BaseRespModel();
        try{
            Subject subject = SecurityUtils.getSubject();
            User user = (User)subject.getPrincipal();
            if(user==null){
                resp.setData(0);
            }else {
                resp.setData(cartService.count(user.getId()));
            }
            resp.setErrmsg("成功");
            resp.setErrno(0);
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
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

}

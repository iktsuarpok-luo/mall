package com.cskaoyan.mall.controller.wjw;

import com.alibaba.fastjson.JSONObject;
import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.Couponuser;
import com.cskaoyan.mall.service.wjw.CouponService;
import com.cskaoyan.mall.service.wjw.CouponUserService;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @author ethan
 * @date 2019/8/16 17:08
 */
@RestController
@RequestMapping("/admin/coupon")
public class CouponController {
    @Autowired
    CouponService couponService;
    @Autowired
    CouponUserService couponUserService;
    /*显示及查询*/
    @RequestMapping("list")
    public BaseRespModel getList(int page,int limit,String sort,String order,String name,Short status,Short type){
        PageHelper.startPage(page,limit);
        List<Coupon> couponList = couponService.selectList(name,status,type,sort,order);
        BaseRespModel<HashMap> baseRespModel = new BaseRespModel<>();
        HashMap<String, Object> data = new HashMap<>();
        PageInfo<Coupon> pageInfo = new PageInfo<>(couponList);
        long total = pageInfo.getTotal();
        data.put("total",total);
        data.put("items",couponList);
        baseRespModel.setErrno(0);
        baseRespModel.setErrmsg("成功");
        baseRespModel.setData(data);
        return baseRespModel;
    }
    /*详情显示功能*/
    @RequestMapping("read")
    public BaseRespModel read(Integer id){
        BaseRespModel<Coupon> baseRespModel = new BaseRespModel<>();
        Coupon coupon=couponService.selectById(id);
        baseRespModel.setData(coupon);
        baseRespModel.setErrno(0);
        baseRespModel.setErrmsg("成功");
        return baseRespModel;
    }
    /*用户详情*/
    @RequestMapping("listuser")
    public BaseRespModel getListUser(int page,int limit,String order,String sort,Short status,Integer couponId,Integer userId){
        List<Couponuser> couponuserList =couponUserService.selectListUser(status,couponId,userId,order,sort);
        BaseRespModel<HashMap> baseRespModel = new BaseRespModel<>();
        HashMap<String, Object> data = new HashMap<>();
        PageInfo<Couponuser> pageInfo = new PageInfo<>(couponuserList);
        long total = pageInfo.getTotal();
        data.put("total",total);
        data.put("items",couponuserList);
        baseRespModel.setErrno(0);
        baseRespModel.setErrmsg("成功");
        baseRespModel.setData(data);
        return baseRespModel;

    }
    /*增加部分*/
    @RequestMapping("create")
    public BaseRespModel create(@RequestBody JSONObject jsonObject){
        jsonObject.put("goodsValue","[]");
        Coupon coupon = jsonObject.toJavaObject(Coupon.class);
        /*如果是兑换码*/
        if (coupon.getType()==2) {
            String code = couponService.generateCode();
            coupon.setCode(code);
        }
        BaseRespModel<Coupon> baseRespModel = new BaseRespModel<>();
        int i = couponService.add(coupon);
        Coupon coupon1 = couponService.selectByName(coupon.getName());
        if (i>0){
            baseRespModel.setData(coupon1);
            baseRespModel.setErrmsg("成功");
            baseRespModel.setErrno(0);
            return baseRespModel;
        }else {
            return null;
        }
    }
    /*删*/
    @RequestMapping("delete")
    public BaseRespModel delete(@RequestBody Coupon coupon){
        BaseRespModel<Object> respModel = new BaseRespModel<>();
        couponService.delete(coupon);
        respModel.setErrmsg("成功");
        respModel.setErrno(0);
        return respModel;
    }
    /*改*/
    @RequestMapping("update")
    public BaseRespModel update(@RequestBody JSONObject jsonObject){
        jsonObject.put("goodsValue","[]");
        Coupon coupon = jsonObject.toJavaObject(Coupon.class);
        BaseRespModel<Coupon> respModel = new BaseRespModel<>();
        int resultAd =couponService.update(coupon);
        if (resultAd>0){
            respModel.setData(coupon);
            respModel.setErrno(0);
            respModel.setErrmsg("成功");
            return respModel;
        }else {
            return null;
        }
    }
}

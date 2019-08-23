package com.cskaoyan.mall.controller.wjw;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.Couponuser;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.service.wjw.CouponService;
import com.cskaoyan.mall.service.wjw.CouponUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author ethan
 * @date 2019/8/21 16:24
 */
@RestController
@RequestMapping("wx/coupon")
public class WxCouponController {
    @Autowired
    CouponService couponService;

    @Autowired
    CouponUserService couponUserService;
    /*显示部分*/
    @RequestMapping("list")
    public BaseRespModel getList(Integer page,Integer size){
        PageHelper.startPage(page,size);
        List<Coupon> couponList = couponService.selectList(null,null,null,"id","desc");
        BaseRespModel<HashMap> baseRespModel = new BaseRespModel<>();
        HashMap<String, Object> data = new HashMap<>();
        PageInfo<Coupon> pageInfo = new PageInfo<>(couponList);
        long total = pageInfo.getTotal();
        data.put("count",total);
        data.put("data",couponList);
        baseRespModel.setErrno(0);
        baseRespModel.setErrmsg("成功");
        baseRespModel.setData(data);
        return baseRespModel;

    }
    /*优惠卷接收相关*/
    @RequestMapping("receive")
    public BaseRespModel receive(@RequestBody JSONObject JcouponId){
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        Integer userId = user.getId();
        Integer couponId = JcouponId.getInteger("couponId");
        BaseRespModel<Coupon> baseRespModel = new BaseRespModel<>();
        Coupon coupon = couponService.selectById(couponId);

        //优惠券领取完
        Integer total = coupon.getTotal();
        Integer totalCoupon = couponUserService.CountCouponById(couponId);
        if ((total!=0)&&totalCoupon>=total){
            baseRespModel.setErrno(700);
            baseRespModel.setErrmsg("优惠券已领完");
            return baseRespModel;
        }
        //优惠券已领取过,如果limit为0不限制，1则限领一张
        int limit = coupon.getLimit().intValue();
        int number = couponUserService.countByUseIdAndCoupId(userId,couponId);
        if (limit!=0&&number>=limit){
            baseRespModel.setErrno(740);
            baseRespModel.setErrmsg("优惠券已经领取过");
            return baseRespModel;
        }
        //优惠券已过期
        Couponuser couponuser = new Couponuser();
        Date endTime = couponuser.getEndTime();
        Date now = new Date();
        if (endTime.getTime()-now.getTime()<0){
            baseRespModel.setErrno(701);
            baseRespModel.setErrmsg("优惠券已经过期");
        }
        //优惠券添加成功
        couponuser.setCouponId(couponId);
        couponuser.setUserId(userId);
        couponUserService.add(couponuser);
        baseRespModel.setErrmsg("领券成功");
        baseRespModel.setErrno(0);
        return baseRespModel;
    }
    @RequestMapping("mylist")
    public BaseRespModel getMyList(Integer page,Integer size,short status){
        PageHelper.startPage(page,size);
        BaseRespModel<HashMap<String, Object>> baseRespModel = new BaseRespModel<>();
        List<Coupon> couponList = couponService.selectListBystatus(status);
        HashMap<String, Object> data = new HashMap<>();
        PageInfo<Coupon> pageInfo = new PageInfo<>(couponList);
        long total = pageInfo.getTotal();
        data.put("count",total);
        data.put("data",couponList);
        baseRespModel.setData(data);
        baseRespModel.setErrmsg("成功");
        baseRespModel.setErrno(0);
        return baseRespModel;
    }
    @RequestMapping("exchange")
    public BaseRespModel exchange(@RequestBody JSONObject Jcode){
        BaseRespModel<HashMap<String, Object>> respModel = new BaseRespModel<>();
        String code = Jcode.getString("code");
        List<Coupon> couponList = couponService.selectListByCode(code);
        if (couponList==null){
            respModel.setErrno(742);
            respModel.setErrmsg("找不到该优惠券");
            return respModel;
        }else {
            HashMap<String, Object> data = new HashMap<>();
            PageInfo<Coupon> pageInfo = new PageInfo<>(couponList);
            long total = pageInfo.getTotal();
            data.put("count",total);
            data.put("data",couponList);
            respModel.setData(data);
            respModel.setErrmsg("成功");
            return respModel;
        }
    }
}

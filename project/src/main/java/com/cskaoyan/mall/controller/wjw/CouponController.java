package com.cskaoyan.mall.controller.wjw;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.Couponuser;
import com.cskaoyan.mall.service.wjw.CouponService;
import com.cskaoyan.mall.service.wjw.CouponUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    /*查*/
    @RequestMapping("list")
    public BaseRespModel getList(int page,int limit,String sort,String order,String name,int status,String type){
        List<Coupon> couponList = couponService.selectList(name,status,type);
        couponList = couponList.subList(limit * (page - 1), Math.min(couponList.size(), limit * page));
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
    /*用户的优惠券*/
    @RequestMapping("listuser")
    public BaseRespModel getListUser(int page,int limit,String order,String sort,short status,int couponId){
        List<Couponuser> couponuserList =couponUserService.selectListUser(status,couponId);
        couponuserList = couponuserList.subList(limit * (page - 1), Math.min(couponuserList.size(), limit * page));
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
    /*详情页显示*/
    @RequestMapping("read")
    public BaseRespModel read(@RequestBody Coupon coupon){
        BaseRespModel<Object> respModel = new BaseRespModel<>();
        return respModel;
    }
    /*增*/
    @RequestMapping("create")
    public BaseRespModel create(@RequestBody Coupon coupon){
        int i = couponService.add(coupon);
        BaseRespModel<Coupon> baseRespModel = new BaseRespModel<>();
        if (i>1){
            baseRespModel.setData(coupon);
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
    @RequestMapping("/update")
    public BaseRespModel update(@RequestBody Coupon coupon){
        BaseRespModel<Coupon> respModel = new BaseRespModel<>();
        int resultAd =couponService.update(coupon);
        if (resultAd>1){
            respModel.setData(coupon);
            respModel.setErrno(0);
            respModel.setErrmsg("成功");
            return respModel;
        }else {
            return null;
        }
    }
}

package com.cskaoyan.mall.controller.wjw;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.service.wjw.CouponService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping("/list")
    public BaseRespModel getList(int page,int limit,String sort,String order){
        PageHelper.startPage(page,limit);
        List<Coupon> couponList = couponService.selectList(sort,order);
        BaseRespModel<HashMap> baseRespModel = new BaseRespModel<>();
        HashMap<String, Object> data = new HashMap<>();
        /*得到total值*/
        PageInfo<Coupon> pageInfo = new PageInfo<>(couponList);
        long total = pageInfo.getTotal();
        data.put("total",total);
        /*得到items*/
        data.put("items",couponList);
        /*设置baseRespModel*/
        baseRespModel.setErrno(0);
        baseRespModel.setErrmsg("成功");
        baseRespModel.setData(data);
        return baseRespModel;
    }
    @RequestMapping("/create")
    public BaseRespModel create(Coupon coupon){
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
}

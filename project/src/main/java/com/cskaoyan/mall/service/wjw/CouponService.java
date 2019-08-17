package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.Coupon;

import java.util.List;

/**
 * @author ethan
 * @date 2019/8/16 17:09
 */
public interface CouponService {
    List<Coupon> selectList(String sort, String order);

    int add(Coupon coupon);
}

package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.Couponuser;

import java.util.List;

/**
 * @author ethan
 * @date 2019/8/17 20:14
 */
public interface CouponUserService {
    List<Couponuser> selectListUser(Short status, Integer couponId,Integer userId,String sort,String order);
}

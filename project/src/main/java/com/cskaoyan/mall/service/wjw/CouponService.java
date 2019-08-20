package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.Coupon;

import java.util.List;

/**
 * @author ethan
 * @date 2019/8/16 17:09
 */
public interface CouponService {

    int add(Coupon coupon);
    void delete(Coupon coupon);
    int update(Coupon coupon);

    List<Coupon> selectList(String name, Short status, Short type,String sort,String order);

    Coupon selectById(Integer id);

    String generateCode();

}

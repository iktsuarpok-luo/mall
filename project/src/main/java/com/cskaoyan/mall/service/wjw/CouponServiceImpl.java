package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.mapper.CouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ethan
 * @date 2019/8/16 17:09
 */
@Service
public class CouponServiceImpl implements CouponService{
    @Autowired
    CouponMapper couponMapper;

    @Override
    public List<Coupon> selectList(String sort, String order) {
        return couponMapper.selectCouponsBySortAndOrder(sort,order);
    }

    @Override
    public int add(Coupon coupon) {
        return couponMapper.insert(coupon);
    }
}

package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.CouponExample;
import com.cskaoyan.mall.bean.Couponuser;
import com.cskaoyan.mall.bean.CouponuserExample;
import com.cskaoyan.mall.mapper.CouponuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ethan
 * @date 2019/8/17 20:14
 */
@Service
public class CouponUserServiceImpl implements CouponUserService {
    @Autowired
    CouponuserMapper couponuserMapper;
    @Override
    public List<Couponuser> selectListUser(short status, int couponId) {
        CouponuserExample couponuserExample = new CouponuserExample();
        if (status!=0&&couponId!=0){
            return couponuserMapper.selectByStatusAndCouponId(status,couponId);
        }else if (status!=0){
            couponuserExample.createCriteria().andStatusEqualTo(status);
            return couponuserMapper.selectByExample(couponuserExample);
        }else if (couponId!=0){
            couponuserExample.createCriteria().andCouponIdEqualTo(couponId);
            return couponuserMapper.selectByExample(couponuserExample);
        }else {
            return couponuserMapper.selectByExample(couponuserExample);
        }
    }
}

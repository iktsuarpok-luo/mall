package com.cskaoyan.mall.service.wjw;

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
    public List<Couponuser> selectListUser(Short status, Integer couponId,Integer userId,String order,String sort) {
        CouponuserExample couponuserExample = new CouponuserExample();
        CouponuserExample.Criteria criteria = couponuserExample.createCriteria();
        if (status!=null){
            criteria.andStatusEqualTo(status);
        }
        if (couponId!=-1){
            criteria.andCouponIdEqualTo(couponId);
        }
        if (userId!=null){
            criteria.andUserIdEqualTo(userId);
        }
        couponuserExample.setOrderByClause(sort+" "+order);
        return couponuserMapper.selectByExample(couponuserExample);
    }
}

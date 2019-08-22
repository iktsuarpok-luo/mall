package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.CouponExample;
import com.cskaoyan.mall.bean.Couponuser;
import com.cskaoyan.mall.bean.CouponuserExample;
import com.cskaoyan.mall.mapper.CouponuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public Integer CountCouponById(Integer couponId) {
        CouponuserExample couponuserExample = new CouponuserExample();
        couponuserExample.or().andCouponIdEqualTo(couponId).andDeletedEqualTo(false);
        return (int)couponuserMapper.countByExample(couponuserExample);
    }

    @Override
    public int countByUseIdAndCoupId(Integer userId, Integer couponId) {
        CouponuserExample couponuserExample = new CouponuserExample();
        couponuserExample.or().andUserIdEqualTo(userId).andCouponIdEqualTo(couponId).andDeletedEqualTo(false);
        return (int)couponuserMapper.countByExample(couponuserExample);
    }

    @Override
    public int add(Couponuser couponuser) {
        couponuser.setAddTime(LocalDateTime.now());
        couponuser.setUpdateTime(LocalDateTime.now());
        return couponuserMapper.insert(couponuser);
    }
}

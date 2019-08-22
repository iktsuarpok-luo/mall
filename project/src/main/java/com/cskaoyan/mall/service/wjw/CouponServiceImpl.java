package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponExample;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public int add(Coupon coupon) {
        coupon.setAddTime(LocalDateTime.now());
        coupon.setUpdateTime(LocalDateTime.now());
        return couponMapper.insert(coupon);
    }

    @Override
    public void delete(Coupon coupon) {
        Integer id = coupon.getId();
        couponMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Coupon coupon) {
        coupon.setUpdateTime(LocalDateTime.now());
        return couponMapper.updateByPrimaryKey(coupon);
    }

    @Override
    public List<Coupon> selectList(String name, Short status, Short type,String sort,String order) {
        CouponExample couponExample = new CouponExample();
        CouponExample.Criteria criteria = couponExample.createCriteria();
        if (name!=null){
            criteria.andNameLike("%"+name+"%");
        }
        if (type!=null){
            criteria.andTypeEqualTo(type);
        }
        if (status!=null){
            criteria.andStatusEqualTo(status);
        }
        couponExample.setOrderByClause(sort+" "+order);
        return couponMapper.selectByExample(couponExample);
    }

    @Override
    public Coupon selectById(Integer id) {
        Coupon coupon = couponMapper.selectByPrimaryKey(id);
        return coupon;
    }

    @Override
    public String generateCode() {
        String random = RandomUtils.getRandomString(8);
        return random;
    }
}

package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.AdExample;
import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponExample;
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
    public List<Coupon> selectList(String name, String content) {
        CouponExample couponExample = new CouponExample();
        if (name!=null&&content!=null){
            return couponMapper.selectByNameAndContent(name,content);
        }else if (name!=null){
            couponExample.createCriteria().andNameLike("%"+name+"%");
            return couponMapper.selectByExample(couponExample);
        }else if (content!=null){
            couponExample.createCriteria().andNameLike("%"+content+"%");
            return couponMapper.selectByExample(couponExample);
        }else {
            return couponMapper.selectByExample(couponExample);
        }
    }

    @Override
    public int add(Coupon coupon) {
        return couponMapper.insert(coupon);
    }

    @Override
    public void delete(Coupon coupon) {
        Integer id = coupon.getId();
        couponMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Coupon coupon) {
        int i = couponMapper.updateByPrimaryKeySelective(coupon);
        return i;
    }
}

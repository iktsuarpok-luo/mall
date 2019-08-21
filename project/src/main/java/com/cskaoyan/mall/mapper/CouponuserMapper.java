package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Couponuser;
import com.cskaoyan.mall.bean.CouponuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponuserMapper {
    long countByExample(CouponuserExample example);

    int deleteByExample(CouponuserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Couponuser record);

    int insertSelective(Couponuser record);

    List<Couponuser> selectByExample(CouponuserExample example);

    Couponuser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Couponuser record, @Param("example") CouponuserExample example);

    int updateByExample(@Param("record") Couponuser record, @Param("example") CouponuserExample example);

    int updateByPrimaryKeySelective(Couponuser record);

    int updateByPrimaryKey(Couponuser record);
    /*扩展部分*/
    List<Couponuser> selectByStatusAndCouponId(short status, int couponId);
}
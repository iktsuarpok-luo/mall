package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Goodsattribute;
import com.cskaoyan.mall.bean.GoodsattributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import javax.management.Attribute;

public interface GoodsattributeMapper {
    long countByExample(GoodsattributeExample example);

    int deleteByExample(GoodsattributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goodsattribute record);

    int insertSelective(Goodsattribute record);

    List<Goodsattribute> selectByExample(GoodsattributeExample example);

    Goodsattribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goodsattribute record, @Param("example") GoodsattributeExample example);

    int updateByExample(@Param("record") Goodsattribute record, @Param("example") GoodsattributeExample example);

    int updateByPrimaryKeySelective(Goodsattribute record);

    int updateByPrimaryKey(Goodsattribute record);


    List<Goodsattribute> findGoodsattributesByGoodsId(Integer goodsId);

    int updateByGoodsId(Attribute[] attributes);
}
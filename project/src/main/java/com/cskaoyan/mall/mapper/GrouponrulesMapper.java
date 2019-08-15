package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Grouponrules;
import com.cskaoyan.mall.bean.GrouponrulesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GrouponrulesMapper {
    long countByExample(GrouponrulesExample example);

    int deleteByExample(GrouponrulesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Grouponrules record);

    int insertSelective(Grouponrules record);

    List<Grouponrules> selectByExample(GrouponrulesExample example);

    Grouponrules selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Grouponrules record, @Param("example") GrouponrulesExample example);

    int updateByExample(@Param("record") Grouponrules record, @Param("example") GrouponrulesExample example);

    int updateByPrimaryKeySelective(Grouponrules record);

    int updateByPrimaryKey(Grouponrules record);
}
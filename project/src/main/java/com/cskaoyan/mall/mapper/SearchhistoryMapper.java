package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Searchhistory;
import com.cskaoyan.mall.bean.SearchhistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SearchhistoryMapper {
    long countByExample(SearchhistoryExample example);

    int deleteByExample(SearchhistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Searchhistory record);

    int insertSelective(Searchhistory record);

    List<Searchhistory> selectByExample(SearchhistoryExample example);

    Searchhistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Searchhistory record, @Param("example") SearchhistoryExample example);

    int updateByExample(@Param("record") Searchhistory record, @Param("example") SearchhistoryExample example);

    int updateByPrimaryKeySelective(Searchhistory record);

    int updateByPrimaryKey(Searchhistory record);
}
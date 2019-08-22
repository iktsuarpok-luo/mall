package com.cskaoyan.mall.mapper.zt;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.CategoryExample;
import com.cskaoyan.mall.bean.zt.ZtCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZtCategoryMapper {
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List findcategoryIdAndPidById(int id);

    List findSonCategory();//查询子分类

    List<ZtCategory> findCategoryList();
    List<ZtCategory> findCategoryList(@Param("pid") int pid);

    /*int findcategoryIdById(int id);

    int findcategoryPidById(int id);*/
}
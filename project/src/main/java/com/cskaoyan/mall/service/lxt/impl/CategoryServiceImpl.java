package com.cskaoyan.mall.service.lxt.impl;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.CategoryExample;
import com.cskaoyan.mall.bean.RegionExample;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.service.lxt.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public List<Category> getCategoryList() {
        CategoryExample example = new CategoryExample();
        example.createCriteria().andPidEqualTo(0);
        List<Category> list = categoryMapper.selectByExample(example);
        list = getChildren(list);
        return list;
    }

    @Override
    public List<Category> getFirstCategory() {
        CategoryExample example = new CategoryExample();
        example.createCriteria().andLevelEqualTo("L1");
        List<Category> list = categoryMapper.selectByExample(example);

        return list;
    }

    private List<Category> getChildren(List<Category> list) {
        for (Category category : list) {
                CategoryExample categoryExample = new CategoryExample();
                categoryExample.createCriteria().andPidEqualTo(category.getId());
                List<Category> children = categoryMapper.selectByExample(categoryExample);
                if(children!=null && children.size()!=0){
                    children=getChildren(children);
                }else{
                    children=null;
            }
            category.setChildren(children);
        }
        return list;
    }
}

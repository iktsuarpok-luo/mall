package com.cskaoyan.mall.service.lxt.impl;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.CategoryExample;
import com.cskaoyan.mall.bean.RegionExample;
import com.cskaoyan.mall.bean.StorageExample;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.mapper.StorageMapper;
import com.cskaoyan.mall.service.lxt.CategoryService;
import com.cskaoyan.mall.service.lxt.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    StorageService storageService;
    @Autowired
    StorageMapper storageMapper;
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

    @Override
    public Category add(Category category) {
        if(category.getPid()==0){
            category.setLevel("L1");
        }
        category.setDeleted(false);
        category.setSortOrder((byte) 100);
        Date date = new Date();
        category.setAddTime(date);
        category.setUpdateTime(date);

        categoryMapper.insert(category);
        return category;
    }

    @Override
    public Category update(Category category) {
        Category category1 = categoryMapper.selectByPrimaryKey(category.getId());
        if(!category1.getPicUrl().equals(category.getPicUrl())){
            storageService.deleteByUrl(category1.getPicUrl());

            StorageExample storageExample = new StorageExample();
            storageExample.createCriteria().andUrlEqualTo(category1.getPicUrl());
            storageMapper.deleteByExample(storageExample);
        }
        if(!category1.getIconUrl().equals(category.getIconUrl())){
            storageService.deleteByUrl(category1.getIconUrl());

            StorageExample storageExample = new StorageExample();
            storageExample.createCriteria().andUrlEqualTo(category1.getIconUrl());
            storageMapper.deleteByExample(storageExample);
        }
        category.setUpdateTime(new Date());
        categoryMapper.updateByPrimaryKey(category);
        return category;
    }

    @Override
    public void delete(Category category) {
        storageService.deleteByUrl(category.getPicUrl());
        storageService.deleteByUrl(category.getIconUrl());

        StorageExample storageExample = new StorageExample();
        List<String> UrlList = new ArrayList<>();
        UrlList.add(category.getIconUrl());
        UrlList.add(category.getPicUrl());
        storageExample.createCriteria().andUrlIn(UrlList);
        storageMapper.deleteByExample(storageExample);

        categoryMapper.deleteByPrimaryKey(category.getId());

    }

    @Override
    public Category getCurrentCategory(int id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Category> getSubCategory(int id) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andPidEqualTo(id);
        return categoryMapper.selectByExample(categoryExample);
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

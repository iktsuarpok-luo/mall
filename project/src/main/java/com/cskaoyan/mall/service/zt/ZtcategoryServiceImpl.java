package com.cskaoyan.mall.service.zt;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.CategoryExample;
import com.cskaoyan.mall.bean.zt.ZtCategory;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.mapper.zt.ZtCategoryMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ZtcategoryServiceImpl implements ZtcategoryService {

    @Autowired
    ZtCategoryMapper ztCategoryMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public List findcategoryIdsById(int id) {
        return ztCategoryMapper.findcategoryIdAndPidById(id);
    }

    @Override
    public List<Map> showCategoryList() {
        List<Map> categoryLists = new ArrayList<>();
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andPidEqualTo(0);
        List<Category> bigCategories = categoryMapper.selectByExample(categoryExample);
        for (Category category : bigCategories) {
            Map<String, Object> categoryMap = new HashMap<>();
            Integer id = category.getId();
            categoryMap.put("value", id);
            String name = category.getName();
            categoryMap.put("label", name);
            categoryExample = new CategoryExample();
            categoryExample.createCriteria().andPidEqualTo(id);
            List<Category> smallCategoryLists = categoryMapper.selectByExample(categoryExample);
            List<Map> children = new ArrayList<>();
            for (Category c : smallCategoryLists) {
                Map<String, Object> tempMap = new HashMap<>();
                Integer categoryId = c.getId();
                String categoryName = c.getName();
                tempMap.put("value", categoryId);
                tempMap.put("label", categoryName);
                children.add(tempMap);
            }
            categoryMap.put("children", children);
            categoryLists.add(categoryMap);
        }
        /*HashMap map = new HashMap();
        map.put("value",);
        map.put("label",)
        List<ZtCategory> categoryList = ztCategoryMapper.findCategoryListByPidEqualZero();
        List<Integer> pidList = null;
        for (int i = 0; i <categoryList.size() ; i++) {
            //获取每个pid，装进pidList
             pidList=categoryList.stream().map(ZtCategory::getVaule).collect(Collectors.toList());
        }
        List childrenList = null;
        for (int i = 0; i < pidList.size(); i++) {
            childrenList.add(ztCategoryMapper.findSonCategoryByPid(pidList.get(i)));
        }
        for (int i = 0; i <categoryList.size() ; i++) {
            categoryList.addAll(childrenList);
        }
        categoryList.addAll(childrenList);*/
        return categoryLists;
    }

   /* @Override
    public List<ZtCategory> showCategoryList() {
        List<ZtCategory> result = new ArrayList<>();
        CategoryExample example = new CategoryExample();
        example.createCriteria().andPidEqualTo(0);
        List<Category> list = categoryMapper.selectByExample(example);
        list = getChildren(list);
        for (Category category : list) {
            ZtCategory ztCategory = new ZtCategory();
            ztCategory.setLabel(category.getName());
            ztCategory.setVaule(category.getId());
            ztCategory.setChildren(new ArrayList<ZtCategory>());
            List<Category> children = category.getChildren();
            for (Category child : children) {
                ZtCategory ztChild = new ZtCategory();
                ztChild.setVaule(child.getId());
                ztChild.setLabel(child.getName());
                ztCategory.getChildren().add(ztChild);
            }
            result.add(ztCategory);
        }
        return result;
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
    }*/
}

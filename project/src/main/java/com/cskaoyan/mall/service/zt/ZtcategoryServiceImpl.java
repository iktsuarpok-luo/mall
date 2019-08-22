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
import java.util.List;
import java.util.Map;

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
    }
}

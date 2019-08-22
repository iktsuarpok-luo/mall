package com.cskaoyan.mall.service.lxt;

import com.cskaoyan.mall.bean.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoryList();

    List<Category> getFirstCategory();

    Category add(Category category);

    Category update(Category category);

    void delete(Category category);

    Category getCurrentCategory(int id);

    List<Category> getSubCategory(int id);
}

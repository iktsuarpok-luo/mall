package com.cskaoyan.mall.service.zt;

import com.cskaoyan.mall.bean.zt.ZtCategory;

import java.util.List;

public interface ZtcategoryService {

    List findcategoryIdsById(int id);

    List<ZtCategory> showCategoryList();
}

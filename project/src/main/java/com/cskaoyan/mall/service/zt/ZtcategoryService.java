package com.cskaoyan.mall.service.zt;

import com.cskaoyan.mall.bean.zt.ZtCategory;

import java.util.List;
import java.util.Map;

public interface ZtcategoryService {

    List findcategoryIdsById(int id);

    List<Map> showCategoryList();
}

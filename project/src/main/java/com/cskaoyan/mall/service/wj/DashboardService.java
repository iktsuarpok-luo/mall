package com.cskaoyan.mall.service.wj;

import com.cskaoyan.mall.bean.GoodsExample;
import com.cskaoyan.mall.bean.GoodsproductExample;
import com.cskaoyan.mall.bean.OrderExample;
import com.cskaoyan.mall.bean.UserExample;

public interface DashboardService {

    long countByExample(UserExample example);

    long countByExample(GoodsExample example);

    long countByExample(GoodsproductExample example);

    long countByExample(OrderExample example);
}

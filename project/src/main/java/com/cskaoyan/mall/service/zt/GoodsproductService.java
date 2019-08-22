package com.cskaoyan.mall.service.zt;

import com.cskaoyan.mall.bean.Goodsproduct;

import java.util.List;

public interface GoodsproductService {
    List<Goodsproduct> findProductById(Integer goodsId);

    void create(String goodsSn, Goodsproduct[] products);
}

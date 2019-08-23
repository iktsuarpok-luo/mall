package com.cskaoyan.mall.service.zt;

import com.cskaoyan.mall.bean.Goodsspecification;

import java.util.List;

public interface GoodsspecificationService {
    List<Goodsspecification> findSpecificationById(int id);

    void create(String goodsSn, Goodsspecification[] specifications);

    List<Goodsspecification> findSpecificationByValueAndGoodsId(Integer id, String name);
}

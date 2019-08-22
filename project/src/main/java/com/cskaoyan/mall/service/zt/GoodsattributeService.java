package com.cskaoyan.mall.service.zt;

import com.cskaoyan.mall.bean.Goodsattribute;

import java.util.List;

public interface GoodsattributeService {

    List<Goodsattribute> findGoodsattributesByGoodsId(Integer goodsID);

    void create(String goodsSn, Goodsattribute[] attributes);

    List<Goodsattribute> findGoodsattributesByGoods(int id);
}

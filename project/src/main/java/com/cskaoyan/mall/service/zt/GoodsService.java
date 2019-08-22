package com.cskaoyan.mall.service.zt;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.Goodsattribute;
import com.cskaoyan.mall.bean.Goodsproduct;
import com.cskaoyan.mall.bean.Goodsspecification;

import javax.management.Attribute;
import java.util.List;

public interface GoodsService {

    List<Goods> findAllGoodsOrByGoodsSnOrByName(String goodSn,String name);

    void delete(Integer id);

    Goods findGoodsById(int id);
    

    Goods add(Goods goods);

    void update(Goods goods, Goodsattribute[] attributes ,Goodsproduct[] products,Goodsspecification[] specifications);

    void create(Goods goods);

    List<Goods> findRelatedGoods(Goods goods);
}

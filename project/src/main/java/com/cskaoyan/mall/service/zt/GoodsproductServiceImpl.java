package com.cskaoyan.mall.service.zt;

import com.cskaoyan.mall.bean.Goodsproduct;
import com.cskaoyan.mall.mapper.GoodsproductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsproductServiceImpl implements GoodsproductService {

    @Autowired
    GoodsproductMapper goodsproductMapper;
    @Override
    public List<Goodsproduct> findProductById(Integer goodsId) {
        return goodsproductMapper.findProductById(goodsId);
    }

    @Override
    public void create(String goodsSn, Goodsproduct[] products) {
        for (Goodsproduct product : products) {
            product.setDeleted(false);
            product.setGoodsId(new Integer(goodsSn));
            goodsproductMapper.insert(product);
        }
    }
}

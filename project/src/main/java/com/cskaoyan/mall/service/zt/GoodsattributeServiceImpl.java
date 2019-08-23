package com.cskaoyan.mall.service.zt;

import com.cskaoyan.mall.bean.Goodsattribute;
import com.cskaoyan.mall.bean.GoodsattributeExample;
import com.cskaoyan.mall.mapper.GoodsattributeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsattributeServiceImpl implements GoodsattributeService {

    @Autowired
    GoodsattributeMapper goodsattributeMapper;
    @Override
    public List<Goodsattribute> findGoodsattributesByGoodsId(Integer goodsId) {
        return goodsattributeMapper.findGoodsattributesByGoodsId(goodsId);
    }

    @Override
    public void create(String goodsSn, Goodsattribute[] attributes) {
        for(Goodsattribute attribute:attributes){
            attribute.setDeleted(false);
            attribute.setGoodsId(new Integer(goodsSn));
            goodsattributeMapper.insert(attribute);
        }
    }

    @Override
    public List<Goodsattribute> findGoodsattributesByGoods(int id) {
        GoodsattributeExample goodsattributeExample = new GoodsattributeExample();
        goodsattributeExample.createCriteria().andGoodsIdEqualTo(id);
        return goodsattributeMapper.selectByExample(goodsattributeExample);
    }
}

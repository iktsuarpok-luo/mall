package com.cskaoyan.mall.service.zt;

import com.cskaoyan.mall.bean.Goodsspecification;
import com.cskaoyan.mall.bean.GoodsspecificationExample;
import com.cskaoyan.mall.mapper.GoodsspecificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsspecificationServiceImpl implements GoodsspecificationService {

    @Autowired
    GoodsspecificationMapper goodsspecificationMapper;
    @Override
    public List<Goodsspecification> findSpecificationById(int id) {
        return goodsspecificationMapper.findSpecificationById(id);
    }

    @Override
    public void create(String goodsSn, Goodsspecification[] specifications) {
        for(Goodsspecification specification:specifications){
            specification.setDeleted(false);
            specification.setGoodsId(new Integer(goodsSn));
            goodsspecificationMapper.insert(specification);
        }
    }

    @Override
    public List<Goodsspecification> findSpecificationByValueAndGoodsId(Integer id, String name) {
        GoodsspecificationExample goodsspecificationExample = new GoodsspecificationExample();
        goodsspecificationExample.createCriteria().andGoodsIdEqualTo(id);
        goodsspecificationExample.createCriteria().andValueEqualTo(name);
        return goodsspecificationMapper.selectByExample(goodsspecificationExample);
    }
}

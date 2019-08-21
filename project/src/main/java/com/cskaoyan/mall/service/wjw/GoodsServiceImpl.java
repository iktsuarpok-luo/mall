package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ethan
 * @date 2019/8/20 15:37
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public Goods selectById(Integer goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId);
    }
}

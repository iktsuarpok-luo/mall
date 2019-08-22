package com.cskaoyan.mall.service.wj.impl;

import com.cskaoyan.mall.bean.GoodsExample;
import com.cskaoyan.mall.bean.GoodsproductExample;
import com.cskaoyan.mall.bean.OrderExample;
import com.cskaoyan.mall.bean.UserExample;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GoodsproductMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.wj.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wj
 * @Date 2019/8/16 15:43
 */
@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsproductMapper goodsproductMapper;
    @Autowired
    OrderMapper orderMapper;

    @Override
    public long countByExample(UserExample example) {
        return userMapper.countByExample(example);
    }

    @Override
    public long countByExample(GoodsExample example) {
        return goodsMapper.countByExample(example);
    }

    @Override
    public long countByExample(GoodsproductExample example) {
        return goodsproductMapper.countByExample(example);
    }

    @Override
    public long countByExample(OrderExample example) {
        return orderMapper.countByExample(example);
    }
}

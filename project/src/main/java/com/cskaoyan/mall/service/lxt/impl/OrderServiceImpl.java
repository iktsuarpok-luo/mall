package com.cskaoyan.mall.service.lxt.impl;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.BrandExample;
import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderExample;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.service.lxt.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Override
    public List<Order> getOrderList(int page, int limit, String sort, String order) {
        OrderExample orderExample = new OrderExample();
        orderExample.setOrderByClause(sort+" "+order);
        List<Order> list = orderMapper.selectByExample(orderExample);
        return list.subList(limit*(page-1),Math.min(limit*page,list.size()));
    }

    @Override
    public long countOrder() {
        return orderMapper.countByExample(new OrderExample());
    }
}

package com.cskaoyan.mall.service.lxt.impl;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.mapper.OrdergoodsMapper;
import com.cskaoyan.mall.service.lxt.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrdergoodsMapper ordergoodsMapper;

    @Override
    public List<Order> getOrderList(int page, int limit, String sort, String order, String orderSn, String userId, Short[] orderStatusArray) {
        OrderExample orderExample = new OrderExample();
        orderExample.setOrderByClause(sort + " " + order);
        if (orderSn != null) {
            orderExample.createCriteria().andOrderSnLike("%" + orderSn + "%");
        }
        if (userId != null && userId != "") {
            try {
                int id = Integer.parseInt(userId);
                orderExample.createCriteria().andUserIdEqualTo(id);
            } catch (Exception e) {
                orderExample.createCriteria().andUserIdEqualTo(null);
            }
        }
        if (orderStatusArray != null && orderStatusArray.length != 0) {
            List orderStatusList = Arrays.asList(orderStatusArray);
            orderExample.createCriteria().andOrderStatusIn(orderStatusList);
        }
        List<Order> list = new ArrayList<>();
        try {
            list = orderMapper.selectByExample(orderExample);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public long countOrder() {
        return orderMapper.countByExample(new OrderExample());
    }

    @Override
    public Order getOrderById(int id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        return order;
    }

    @Override
    public List<Ordergoods> getOrderGoods(int id) {
        OrdergoodsExample ordergoodsExample = new OrdergoodsExample();
        ordergoodsExample.createCriteria().andOrderIdEqualTo(id);
        return ordergoodsMapper.selectByExample(ordergoodsExample);
    }

    @Override
    public int countUnpaid(User user) {
        OrderExample orderExample = new OrderExample();
        List list = new ArrayList();
        list.add(101);
        list.add(102);
        list.add(103);
        orderExample.createCriteria().andUserIdEqualTo(user.getId()).andOrderStatusIn(list);
        int l = (int) orderMapper.countByExample(orderExample);
        return l;
    }

    @Override
    public int countUnship(User user) {
        OrderExample orderExample = new OrderExample();
        List list = new ArrayList();
        list.add(201);
        list.add(202);
        list.add(203);
        orderExample.createCriteria().andUserIdEqualTo(user.getId()).andOrderStatusIn(list);
        int l = (int) orderMapper.countByExample(orderExample);
        return l;
    }

    @Override
    public int countUnrecv(User user) {
        OrderExample orderExample = new OrderExample();
        List list = new ArrayList();
        list.add(301);
        orderExample.createCriteria().andUserIdEqualTo(user.getId()).andOrderStatusIn(list);
        int l = (int) orderMapper.countByExample(orderExample);
        return l;
    }

    @Override
    public int countUncomment(User user) {
        OrderExample orderExample = new OrderExample();
        List list = new ArrayList();
        list.add(401);
        list.add(402);
        orderExample.createCriteria().andUserIdEqualTo(user.getId()).andOrderStatusIn(list);
        int l = (int) orderMapper.countByExample(orderExample);
        return l;
    }

    @Override
    public long countByType(int showType,int id) {

        OrderExample orderExample = new OrderExample();
        if (showType != 0) {
            orderExample.createCriteria().andOrderStatusBetween((short) (showType * 100), (short) ((showType + 1) * 100));
        }
        orderExample.createCriteria().andUserIdEqualTo(id);
        return orderMapper.countByExample(orderExample);
    }


    @Override
    public List<Order> getOrderListByType(int showType, int page, int size,int id) {
        Short[] orderStatusArray = new Short[0];
        switch (showType) {
            case 0:
                orderStatusArray = new Short[]{101, 102, 103, 201, 202, 203, 301, 401, 402};
                break;
            case 1:
                orderStatusArray = new Short[]{101, 102, 103};
                break;
            case 2:
                orderStatusArray = new Short[]{201, 202, 203};
                break;
            case 3:
                orderStatusArray = new Short[]{301};
                break;
            case 4:
                orderStatusArray = new Short[]{401, 402};
                break;
        }

        return this.getOrderList(page, size, "id", "desc", null, String.valueOf(id), orderStatusArray);
    }
}

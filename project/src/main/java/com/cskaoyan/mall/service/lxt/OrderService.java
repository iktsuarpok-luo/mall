package com.cskaoyan.mall.service.lxt;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrderList(int page, int limit, String sort, String order);

    long countOrder();
}

package com.cskaoyan.mall.service.lxt;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.Ordergoods;
import com.cskaoyan.mall.bean.User;

import java.util.List;

public interface OrderService {
    List<Order> getOrderList(int page, int limit, String sort, String order,String orderSn,String userId,Short[] orderStatusArray);

    long countOrder();

    Order getOrderById(int id);

    List<Ordergoods> getOrderGoods(int id);

    int countUnpaid(User user);

    int countUnship(User user);

    int countUnrecv(User user);

    int countUncomment(User user);

    long countByType(int showType,int id);

    List<Order> getOrderListByType(int showType,int page ,int size,int id);

    void update(Order order);
}

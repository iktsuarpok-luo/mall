package com.cskaoyan.mall.service.lxt;

import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.bean.CartTotal;
import com.cskaoyan.mall.bean.Check;

import java.util.List;

public interface CartService {
    List<Cart> getCartList();

    CartTotal check();

    CartTotal updateCheck(Check check);
}

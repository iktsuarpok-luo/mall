package com.cskaoyan.mall.service.lxt.impl;

import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.bean.CartExample;
import com.cskaoyan.mall.bean.CartTotal;
import com.cskaoyan.mall.bean.Check;
import com.cskaoyan.mall.mapper.CartMapper;
import com.cskaoyan.mall.service.lxt.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;
    @Override
    public List<Cart> getCartList() {
        return cartMapper.selectByExample(new CartExample());
    }

    @Override
    public CartTotal check() {
        CartTotal total = new CartTotal();
        CartExample cartExample = new CartExample();
        total.setGoodsAmount(cartMapper.getCountByExample(cartExample));
        cartExample.createCriteria().andCheckedEqualTo(true);
        total.setCheckedGoodsCount(cartMapper.getCountByExample(cartExample));
        CartExample cartExample1 = new CartExample();
        total.setGoodsAmount(cartMapper.getAmountByExample(cartExample1));
        cartExample1.createCriteria().andCheckedEqualTo(true);
        total.setCheckedGoodsAmount(cartMapper.getAmountByExample(cartExample1));
        return total;
    }

    @Override
    public CartTotal updateCheck(Check check) {
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andProductIdIn(check.getProductIds());
        List<Cart> list = cartMapper.selectByExample(cartExample);
        for (Cart cart : list) {
            cart.setChecked(check.getIsChecked());
            cartMapper.updateByPrimaryKey(cart);
        }
        CartTotal total = new CartTotal();
        CartExample cartExample1 = new CartExample();
        total.setGoodsAmount(cartMapper.getCountByExample(cartExample1));
        cartExample1.createCriteria().andCheckedEqualTo(true);
        total.setCheckedGoodsCount(cartMapper.getCountByExample(cartExample1));
        CartExample cartExample2 = new CartExample();
        total.setGoodsAmount(cartMapper.getAmountByExample(cartExample2));
        cartExample1.createCriteria().andCheckedEqualTo(true);
        total.setCheckedGoodsAmount(cartMapper.getAmountByExample(cartExample2));
        return total;
    }

    @Override
    public int countGoods(Integer id) {
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andUserIdEqualTo(id);
        return (int) cartMapper.countByExample(cartExample);
    }

    @Override
    public int count(Integer id) {
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andUserIdEqualTo(id);
        List<Cart> list = cartMapper.selectByExample(cartExample);
        int count = 0;
        for (Cart cart : list) {
            count+=cart.getNumber();
        }
        return count;
    }
}

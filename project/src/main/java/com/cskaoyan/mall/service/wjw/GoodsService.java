package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.Goods;

/**
 * @author ethan
 * @date 2019/8/20 15:36
 */
public interface GoodsService {
    Goods selectById(Integer goodsId);
}

package com.cskaoyan.mall.service.zxin;

import com.cskaoyan.mall.bean.System;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ConfigService {
    HashMap<String, Object> selectMall();

    HashMap<String, Object> selectExpress();

    HashMap<String, Object> selectOrder();

    HashMap<String, Object> selectWx();

    //更新商场配置的方法
    void updateMall(Map<String, Object> map);

    //更新运费配置的方法
    void updateExpress(Map<String, Object> map);

    //更新订单配置的方法
    void updateOrder(Map<String, Object> map);

    //更新小程序配置的方法
    void updateWx(Map<String, Object> map);
}

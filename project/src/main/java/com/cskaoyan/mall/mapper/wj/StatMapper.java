package com.cskaoyan.mall.mapper.wj;

import java.util.List;
import java.util.Map;

/**
 * @author wj
 * @Date 2019/8/17 15:31
 */
public interface StatMapper {

    List<Map> statUser();

    List<Map> statOrder();

    List<Map> statGoods();
}
package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.Groupon;

import java.util.List;

/**
 * @author ethan
 * @date 2019/8/19 9:31
 */
public interface GrouponService {
    List<Groupon> getListRecord(String sort, String order, Integer goodsId);

    List<Groupon> selectGrouponById(Integer id);

}

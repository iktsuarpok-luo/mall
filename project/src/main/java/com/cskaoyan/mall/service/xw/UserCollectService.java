package com.cskaoyan.mall.service.xw;

import com.cskaoyan.mall.bean.Collect;

import java.util.List;

public interface UserCollectService {
    List<Collect> queryCollect(int page, int limit, Integer userId, Integer valueId);

    int count(Integer id, int type);
}

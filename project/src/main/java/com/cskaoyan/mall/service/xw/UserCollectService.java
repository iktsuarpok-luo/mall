package com.cskaoyan.mall.service.xw;

import com.cskaoyan.mall.bean.Collect;

import java.util.List;

public interface UserCollectService {
    List<Collect> queryCollect(int page, int limit, Integer userId, Integer valueId);

    int count(Integer id, int type);

    int check(Integer goodsId, Integer userId, int type);

    Collect addCollect(Collect collect);

    Collect getCollect(Integer userId, Byte type, Integer valueId);

    void delete(Collect collect);

    List<Collect> myCollect(int page, int limit, Integer userId, int type);
}

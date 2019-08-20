package com.cskaoyan.mall.service.xw;

import com.cskaoyan.mall.bean.Footprint;

import java.util.List;

public interface UserFootPrintService {
    List<Footprint> queryFootPrint(int page, int limit, Integer userId, Integer goodsId);
}

package com.cskaoyan.mall.service.xw;

import com.cskaoyan.mall.bean.Searchhistory;

import java.util.List;

public interface UserHistoryService {
    List<Searchhistory> queryHistory(int page, int limit, Integer userId, String keyword);
}

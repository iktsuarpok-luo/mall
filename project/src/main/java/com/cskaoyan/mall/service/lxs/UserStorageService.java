package com.cskaoyan.mall.service.lxs;

import com.cskaoyan.mall.bean.Storage;

import java.util.List;

public interface UserStorageService {
    List<Storage> getStorageList(int page, int limit, String sort, String order, String name, String key);

    long countStorage();

    Storage update(Storage storage);

    void delete(Storage storage);
}

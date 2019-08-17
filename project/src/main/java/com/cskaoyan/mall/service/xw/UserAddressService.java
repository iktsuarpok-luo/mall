package com.cskaoyan.mall.service.xw;

import com.cskaoyan.mall.bean.Address;

import java.util.List;

public interface UserAddressService {
    List<Address> queryAddress(int page, int limit, String userId, String name);
}

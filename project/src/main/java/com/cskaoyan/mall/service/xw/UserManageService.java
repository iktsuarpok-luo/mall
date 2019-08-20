package com.cskaoyan.mall.service.xw;

import com.cskaoyan.mall.bean.User;

import java.util.List;

public interface UserManageService {
    List<User> queryAllUser(int page, int limit, String sort, String order);
    Object getUserById(int id);
}

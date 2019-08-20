package com.cskaoyan.mall.service.lxt;

import com.cskaoyan.mall.bean.User;

public interface UserService {
    Object getUserById(int id);

    void update(User user);
}

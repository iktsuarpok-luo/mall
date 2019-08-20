package com.cskaoyan.mall.service.lxt;

import com.cskaoyan.mall.bean.User;

public interface UserService {


    void update(User user);
    User getUserById(int id);

    User selectByName(String principal);
}

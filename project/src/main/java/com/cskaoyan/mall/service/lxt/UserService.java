package com.cskaoyan.mall.service.lxt;

import com.cskaoyan.mall.bean.User;

public interface UserService {
    User getUserById(int id);

    User selectByName(String principal);
}

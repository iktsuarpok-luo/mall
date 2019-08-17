package com.cskaoyan.mall.service.lxt.impl;

import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.lxt.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public Object getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }
}

package com.cskaoyan.mall.service.lxt.impl;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.UserExample;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.lxt.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User selectByName(String principal) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(principal);
        return userMapper.selectByExample(userExample).get(0);
    }

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public User getUserByMobile(String mobile) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andMobileEqualTo(mobile);
        List<User> user = userMapper.selectByExample(userExample);
        if(user.size()==0){
            return null;
        }else {
            return user.get(0);
        }

    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }
}

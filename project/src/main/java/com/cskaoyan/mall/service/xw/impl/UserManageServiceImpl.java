package com.cskaoyan.mall.service.xw.impl;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.UserExample;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.xw.UserManageService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManageServiceImpl implements UserManageService {
    @Autowired
    UserMapper userMapper;

    //点击会员管理的页面显示，已经查找功能
    @Override
    public List<User> queryAllUser(int page, int  limit, String username, String mobile) {
        UserExample userExample = new UserExample();
        PageHelper.startPage(page,limit);
        //对username和mobile进行判空，来决定是进行页面显示还是查询
        //都为空则是页面显示
        if(username == null || username.equals("")){
            username =null;
        }

        if(mobile == null || mobile.equals("")){
            mobile =null;
        }
        if(username == null && mobile == null){
            List<User> userList = userMapper.selectByExample(userExample);
            return userList;
        }
        //username不为空，则以username模糊查询
        if(username != null && mobile == null){
            String s = "%" + username + "%";
            userExample.createCriteria().andUsernameLike(s);
            List<User> userList = userMapper.selectByExample(userExample);
            return userList;
        }
        //mobile不为空，则以mobile模糊查询
        if(username == null && mobile != null){
            userExample.createCriteria().andMobileEqualTo(mobile);
            List<User> userList = userMapper.selectByExample(userExample);
            return userList;
        }
        //都不为空
        if(username != null && mobile != null){
            String s = "%" + username + "%";
            userExample.createCriteria().andUsernameLike(s).andMobileEqualTo(mobile);
            List<User> userList = userMapper.selectByExample(userExample);
            return userList;
        }
        return null;
    }

    //根据id获得User
    @Override
    public Object getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }
}

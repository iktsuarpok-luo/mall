package com.cskaoyan.mall.service.lxs.impl;


import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.lxs.lxsAdminTwo;
import com.cskaoyan.mall.bean.lxs.lxsFeedback;
import com.cskaoyan.mall.mapper.FeedbackMapper;
import com.cskaoyan.mall.service.lxs.WxFeedbackService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WxFeedbackServiceimpl implements WxFeedbackService {
    @Autowired
    FeedbackMapper feedbackMapper;
    @Override
    public void insert(lxsFeedback lxsfeedback) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        int id = user.getId();
        String username = user.getUsername();
        lxsfeedback.setUserId(id);
        lxsfeedback.setUsername(username);
        lxsfeedback.setUpdateTime(new Date());
        lxsfeedback.setAddTime(new Date());
        lxsfeedback.setDeleted(true);
        lxsfeedback.setStatus(0);
        String[] picUrls = lxsfeedback.getPicUrls();
        if(picUrls.length==0){lxsfeedback.setHasPicture(false);}
        lxsfeedback.setHasPicture(true);
        System.out.println(lxsfeedback);
        feedbackMapper.insertfb(lxsfeedback);
    }
}

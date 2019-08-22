package com.cskaoyan.mall.service.xw.impl;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.bean.FeedbackExample;
import com.cskaoyan.mall.mapper.FeedbackMapper;
import com.cskaoyan.mall.service.xw.UserFeedBackService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFeedBackServiceImpl implements UserFeedBackService {
    @Autowired
    FeedbackMapper feedbackMapper;
    @Override
    public List<Feedback> queryFeedBack(int page, int limit, String username, Integer id) {
        FeedbackExample feedbackExample = new FeedbackExample();
        PageHelper.startPage(page,limit);
        if(username == null || username.equals("")){
            username = null;
        }
        if(id == null || id.equals("")){
            id = null;
        }
        if(username == null && id == null){
            List<Feedback> feedbackList = feedbackMapper.selectByExample(feedbackExample);
            return feedbackList;
        }

        if(username != null && id == null){
            String s = "%" + username + "%";
            feedbackExample.createCriteria().andUsernameLike(s);
            List<Feedback> feedbackList = feedbackMapper.selectByExample(feedbackExample);
            return feedbackList;
        }

        if(username == null && id != null){
            feedbackExample.createCriteria().andIdEqualTo(id);
            List<Feedback> feedbackList = feedbackMapper.selectByExample(feedbackExample);
            return feedbackList;
        }

        if(username != null && id != null){
            String s = "%" + username + "%";
            feedbackExample.createCriteria().andIdEqualTo(id).andUsernameLike(s);
            List<Feedback> feedbackList = feedbackMapper.selectByExample(feedbackExample);
            return feedbackList;
        }
        return null;
    }
}

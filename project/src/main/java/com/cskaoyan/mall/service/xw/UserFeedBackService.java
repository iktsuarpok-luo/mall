package com.cskaoyan.mall.service.xw;

import com.cskaoyan.mall.bean.Feedback;

import java.util.List;

public interface UserFeedBackService {
    List<Feedback> queryFeedBack(int page, int limit, String username, Integer id);
}

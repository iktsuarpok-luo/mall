package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.Topic;

import java.util.List;

/**
 * @author ethan
 * @date 2019/8/19 19:39
 */
public interface TopicService {
    int  add(Topic topic);

    int delete(Topic topic);

    int update(Topic topic);

    List<Topic> getList(String title,String subtitle,String sort,String order);

    List<Topic> getHomeList();

    Topic getDetailById(int id);
}

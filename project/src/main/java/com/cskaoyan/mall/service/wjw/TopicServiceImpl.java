package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.bean.TopicExample;
import com.cskaoyan.mall.mapper.TopicMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ethan
 * @date 2019/8/19 19:40
 */
@Service
public class TopicServiceImpl implements TopicService{
    @Autowired
    private TopicMapper topicMapper;
    @Override
    public int add(Topic topic) {
        topic.setAddTime(LocalDateTime.now());
        topic.setUpdateTime(LocalDateTime.now());
        return topicMapper.insertSelective(topic);
    }

    @Override
    public int delete(Topic topic) {
        Integer id = topic.getId();
        return topicMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Topic topic) {
        topic.setUpdateTime(LocalDateTime.now());
        return topicMapper.updateByPrimaryKey(topic);
    }

    @Override
    public List<Topic> getList(String title,String subtitle,String sort,String order) {
        TopicExample topicExample = new TopicExample();
        TopicExample.Criteria criteria = topicExample.createCriteria();
        if (title!=null){
            criteria.andTitleLike("%"+title+"%");
        }
        if (subtitle!=null){
            criteria.andSubtitleLike("%"+subtitle+"%");
        }
        topicExample.setOrderByClause(sort+" "+order);
        return topicMapper.selectByExample(topicExample);
    }

    //获取首页的四个topic
    @Override
    public List<Topic> getHomeList() {
        TopicExample topicExample = new TopicExample();
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        return topics.subList(0,4);
    }

    //根据id获取topic详情
    @Override
    public Topic getDetailById(int id) {
        return topicMapper.selectByPrimaryKey(id);
    }
}

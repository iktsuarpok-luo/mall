package com.cskaoyan.mall.controller.wjw;

import com.alibaba.fastjson.JSONObject;
import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.service.wjw.TopicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author ethan
 * @date 2019/8/17 21:17
 */
@RestController
@RequestMapping("admin/topic")
public class TopicController {
    @Autowired
    TopicService topicService;

    /*显示及搜索*/
    @RequestMapping("list")
    public BaseRespModel getList(String title,String subtitle,int page,int limit,String sort,String order){
        PageHelper.startPage(page,limit);
        BaseRespModel<HashMap> respModel = new BaseRespModel<>();
        List<Topic> topicList = topicService.getList(title,subtitle,sort,order);
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList);
        long total = pageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("items",topicList);
        map.put("total",total);
        respModel.setData(map);
        respModel.setErrno(0);
        respModel.setErrmsg("成功");
        return respModel;
    }
    /*详情模块*/
    @RequestMapping("read")
    public Object read(@RequestBody Topic topic){
        String content = topic.getContent();
        return content;
    }
    /*增加模块*/
    @RequestMapping("create")
    public BaseRespModel getList(@RequestBody JSONObject jsonObject){
        jsonObject.put("goods","[]");
        Topic topic = jsonObject.toJavaObject(Topic.class);
        BaseRespModel<Topic> baseRespModel = new BaseRespModel<>();
        int add = topicService.add(topic);
        if (add>0){
            baseRespModel.setData(topic);
            baseRespModel.setErrmsg("成功");
            baseRespModel.setErrno(0);
            return baseRespModel;
        }else {
            return null;
        }

    }
    /*删除模块*/
    @RequestMapping("delete")
    public BaseRespModel delete(@RequestBody Topic topic){
        BaseRespModel<Topic> baseRespModel = new BaseRespModel<>();
        int i = topicService.delete(topic);
        if (i>0){
            baseRespModel.setErrno(0);
            baseRespModel.setErrmsg("成功");
            return baseRespModel;
        }else {
            return null;
        }
    }
    @RequestMapping("update")
    public BaseRespModel update(@RequestBody Topic topic){
        BaseRespModel<Topic> baseRespModel = new BaseRespModel<>();
        int i = topicService.update(topic);
        if (i>0){
            baseRespModel.setData(topic);
            baseRespModel.setErrmsg("修改成功");
            baseRespModel.setErrno(0);
            return baseRespModel;
        }else {
            return null;
        }
    }
}

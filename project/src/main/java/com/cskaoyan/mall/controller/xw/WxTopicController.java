package com.cskaoyan.mall.controller.xw;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.service.wjw.TopicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("wx/topic")
public class WxTopicController {
    @Autowired
    TopicService topicService;
    @RequestMapping("list")
    public BaseRespModel topicList(int page,int size){
        PageHelper.startPage(page,size);
        BaseRespModel<HashMap> respModel = new BaseRespModel<>();
        try{
        List<Topic> topicList = topicService.getList(null,null,"id","desc");
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList);
        long total = pageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",topicList);
        map.put("total",total);
        respModel.setData(map);
        respModel.setErrno(0);
        respModel.setErrmsg("成功");
        }catch (Exception e){
            respModel.setErrno(1);
            respModel.setErrmsg("失败");
        }
        return respModel;
    }

    //topic详情
    @RequestMapping("detail")
    public BaseRespModel topicDetail(int id){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            String[] strings = null;
            data.put("goods",strings);
            data.put("topic",topicService.getDetailById(id));
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrmsg("失败");
            resp.setErrno(1);
        }
        return resp;
    }

    //点详情后的topic专题推荐
    @RequestMapping("related")
    public BaseRespModel topicRelated(){
        BaseRespModel resp = new BaseRespModel();
        try {
            resp.setData(topicService.getHomeList());
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrmsg("失败");
            resp.setErrno(1);
        }
        return resp;
    }

}

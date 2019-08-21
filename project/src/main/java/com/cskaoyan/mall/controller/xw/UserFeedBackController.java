package com.cskaoyan.mall.controller.xw;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.bean.PageBean;
import com.cskaoyan.mall.service.xw.UserFeedBackService;
import com.cskaoyan.mall.service.xw.UserFootPrintService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class UserFeedBackController {
    @Autowired
    UserFeedBackService userFeedBackService;
    @RequestMapping("feedback/list")
    public BaseRespModel<PageBean> queryFeedBack(int page, int limit, String username, Integer id){
        BaseRespModel<PageBean> respModel = new BaseRespModel<>();
        try{
        List<Feedback> feedbackList = userFeedBackService.queryFeedBack(page, limit, username, id);
        PageInfo<Feedback> pageInfo = new PageInfo<>(feedbackList);
        PageBean<Feedback> pageBean = new PageBean<>(feedbackList, pageInfo.getTotal());

        respModel.setData(pageBean);
        respModel.setErrmsg("成功");
        respModel.setErrno(0);}
        catch (Exception e){
            respModel.setErrmsg("失败");
            respModel.setErrno(1);
        }
        return respModel;
    }
}

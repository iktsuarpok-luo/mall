package com.cskaoyan.mall.controller.xw;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.xw.XwComment;
import com.cskaoyan.mall.service.xw.WxCommentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("wx/comment")
public class WxCommentController {
    @Autowired
    WxCommentService wxCommentService;
    @RequestMapping("count")
    public BaseRespModel commentCount(int valueId,int type){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            data.put("allCount",wxCommentService.countCommentByValueId(valueId, type));
            data.put("hasPicCount",wxCommentService.hasPicCount(valueId,type));
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
    @RequestMapping("post")
    public BaseRespModel commentPost(@RequestBody XwComment xwComment){
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        BaseRespModel respModel = new BaseRespModel();
        try{Comment comment = new Comment();
        Date date = new Date();
        comment.setAddTime(date);
        comment.setContent(xwComment.getContent());
        comment.setHasPicture(xwComment.getHasPicture());
        comment.setPicUrls(xwComment.getPicUrls());
        comment.setStar(xwComment.getStar());
        comment.setType(xwComment.getType());
        comment.setValueId(xwComment.getValueId());
        comment.setUpdateTime(date);
        comment.setDeleted(false);
        comment.setUserId(user.getId());
        Comment comment1= wxCommentService.insertComment(comment);
        respModel.setData(comment1);
        respModel.setErrno(0);
        respModel.setErrmsg("成功");
        }catch (Exception e){
            respModel.setErrmsg("失败");
            respModel.setErrno(1);
        }
        return respModel;
    }
    @RequestMapping("list")
    public BaseRespModel commentList(int valueId,int type,int showType,int page,int size){
        BaseRespModel resp = new BaseRespModel();
        try {
            Subject subject = SecurityUtils.getSubject();
            User user = (User)subject.getPrincipal();
            HashMap userInfo = new HashMap();
            userInfo.put("avatarUrl",user.getAvatar());
            userInfo.put("nickName",user.getNickname());
            List<Comment> comments = wxCommentService.queryCommentByValueId(valueId,type,page,size,showType);
            HashMap data1 = new HashMap();
            data1.put("count",wxCommentService.countCommentByValueId(valueId,type));
            data1.put("currentPage",page);
            List data = new ArrayList();
            for (Comment comment : comments) {
                HashMap bean = new HashMap();
                bean.put("userInfo",userInfo);
                bean.put("addTime",comment.getAddTime());
                bean.put("content",comment.getContent());
                bean.put("picList",comment.getPicUrls());
                data.add(bean);
            }
            data1.put("data",data);
            resp.setData(data1);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrmsg("失败");
            resp.setErrno(1);
        }
        return resp;
    }
}

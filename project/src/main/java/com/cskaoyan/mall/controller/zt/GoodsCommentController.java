package com.cskaoyan.mall.controller.zt;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.service.zt.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("admin/comment/")
public class GoodsCommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping("list")
    public HashMap findAllComments(int page,int limit){

        PageHelper.startPage(page,limit);
        List<Comment> comments = commentService.findAllComments();
        PageInfo commentsPageInfo = new PageInfo(comments);

        HashMap data = new HashMap<>();
        data.put("items",comments);
        data.put("total",commentsPageInfo.getTotal());

        HashMap map = new HashMap();
        map.put("data",data);
        map.put("errno",0);
        map.put("errmsg","成功");
        return map;
    }

}

package com.cskaoyan.mall.controller.zt;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.service.zt.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
//@RequestMapping("admin/comment/")
public class GoodsCommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping("admin/comment/list")
    public HashMap findAllComments(int page, int limit,Integer userId,Integer valueId){
        PageHelper.startPage(page,limit);
        List<Comment> comments = commentService.findAllCommentsOrByUserIdOrByValueId(userId,valueId);
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

    @RequestMapping("admin/comment/delete")
    public HashMap delete(@RequestBody Comment comment){
        commentService.delete(comment.getId());
        HashMap map = new HashMap<>();
        map.put("errno",0);
        map.put("errmsg","成功");
        return map;
    }

    @RequestMapping("admin/order/reply")
    public HashMap reply(){
        HashMap map = new HashMap<>();
        map.put("errno",622);
        map.put("errmsg","订单商品已回复！");
        return map;
    }

}

package com.cskaoyan.mall.service.xw;

import com.cskaoyan.mall.bean.Comment;

import java.util.List;

public interface WxCommentService {
    List<Comment> queryCommentByValueId(int valueId,int type,int page,int size,int showType);

    int countCommentByValueId(int valueId, int type);

    Comment insertComment(Comment comment);

    int hasPicCount(int valueId, int type);
}

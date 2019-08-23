package com.cskaoyan.mall.service.zt;

import com.cskaoyan.mall.bean.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllCommentsOrByUserIdOrByValueId(Integer userId,Integer valueId);

    void delete(Integer id);

    int countComment(Integer id, int i);

    List<Comment> selectComments(Integer id, int i);
}

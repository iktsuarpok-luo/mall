package com.cskaoyan.mall.service.xw.impl;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.CommentExample;
import com.cskaoyan.mall.mapper.CommentMapper;
import com.cskaoyan.mall.service.xw.WxCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxCommentServiceImpl implements WxCommentService {
    @Autowired
    CommentMapper commentMapper;
    @Override
    public List<Comment> queryCommentByValueId(int valueId, int type,int page,int size,int showType) {
        CommentExample commentExample = new CommentExample();
        if(showType == 0) {
            commentExample.createCriteria().andValueIdEqualTo(valueId).andTypeEqualTo(((byte) type));
        }else {
            commentExample.createCriteria().andValueIdEqualTo(valueId).andTypeEqualTo(((byte) type)).andPicUrlsNotEqualTo("[]");
        }
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        return comments.subList((page -1)*size,Math.min(page*size,comments.size()));
    }

    //根据id和type查询条数
    @Override
    public int countCommentByValueId(int valueId, int type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andValueIdEqualTo(valueId).andTypeEqualTo(((byte) type));
        int l = (int)commentMapper.countByExample(commentExample);
        return l;
    }

    @Override
    public Comment insertComment(Comment comment) {
        commentMapper.insert(comment);
        return comment;
    }

    //查询带有图片的评论数
    @Override
    public int hasPicCount(int valueId, int type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andTypeEqualTo(((byte) type)).andValueIdEqualTo(valueId).andPicUrlsNotEqualTo("[]");
        int l = (int)commentMapper.countByExample(commentExample);
        return l;
    }
}

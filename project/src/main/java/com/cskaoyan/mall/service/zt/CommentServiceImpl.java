package com.cskaoyan.mall.service.zt;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.CommentExample;
import com.cskaoyan.mall.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> findAllComments() {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andIdIsNotNull();
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        return comments;
    }
}

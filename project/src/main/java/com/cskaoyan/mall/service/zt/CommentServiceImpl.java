package com.cskaoyan.mall.service.zt;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.CommentExample;
import com.cskaoyan.mall.mapper.CommentMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> findAllCommentsOrByUserIdOrByValueId(Integer userId,Integer valueId) {
        List<Comment> comments = null;
        if (userId == null  && valueId == null ) {
            CommentExample commentExample = new CommentExample();
            commentExample.createCriteria().andIdIsNotNull();
            comments = commentMapper.selectByExample(commentExample);
        }else if (userId!=null){
            comments = commentMapper.findCommentByUserId(userId);
        }else if (valueId!=null){
            comments = commentMapper.findCommentByVauleId(valueId);
        }
        return comments;
    }

    @Override
    public void delete(Integer id) {
        commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int countComment(Integer id, int i) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andValueIdEqualTo(id).andTypeEqualTo((byte) i);
        return (int) commentMapper.countByExample(commentExample);
    }

    @Override
    public List<Comment> selectComments(Integer id, int i) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andValueIdEqualTo(id).andTypeEqualTo((byte) i);
        return commentMapper.selectByExample(commentExample);
    }
}

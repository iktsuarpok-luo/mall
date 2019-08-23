package com.cskaoyan.mall.service.lxt.impl;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.BrandExample;
import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.bean.IssueExample;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.mapper.IssueMapper;
import com.cskaoyan.mall.service.lxt.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    IssueMapper issueMapper;
    @Override
    public List<Issue> getIssueList(int page, int limit, String sort, String order,String question) {
        IssueExample issueExample = new IssueExample();
        issueExample.setOrderByClause(sort+" "+order);
        if(question!=null){
            issueExample.createCriteria().andQuestionLike("%"+question+"%");
        }
        List<Issue> list = issueMapper.selectByExample(issueExample);
        return list.subList(limit*(page-1),Math.min(limit*page,list.size()));
    }

    @Override
    public long countIssue() {
        return issueMapper.countByExample(new IssueExample());
    }

    @Override
    public Issue create(Issue issue) {
        issue.setDeleted(false);
        Date date = new Date();
        issue.setAddTime(date);
        issue.setUpdateTime(date);
        issueMapper.insert(issue);
        return issue;
    }

    @Override
    public void delete(Integer id) {
        issueMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Issue update(Issue issue) {
        Date date = new Date();
        issue.setUpdateTime(date);
        issueMapper.updateByPrimaryKey(issue);
        return issue;
    }

    @Override
    public List<Issue> getAllIssue() {
        return issueMapper.selectByExample(new IssueExample());
    }
}

package com.cskaoyan.mall.service.lxt;

import com.cskaoyan.mall.bean.Issue;

import java.util.List;

public interface IssueService {
    List<Issue> getIssueList(int page, int limit, String sort, String order,String question);

    long countIssue();

    Issue create(Issue issue);

    void delete(Integer id);

    Issue update(Issue issue);
}

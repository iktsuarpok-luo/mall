package com.cskaoyan.mall.service.lxt;

import com.cskaoyan.mall.bean.Keyword;

import java.util.List;

public interface KeywordService {
    long countKeyword();

    List<Keyword> getKeywordList(int page, int limit, String sort, String order,String keyword,String url);

    Keyword create(Keyword keyword);

    void delete(Integer id);

    Keyword update(Keyword keyword);
}

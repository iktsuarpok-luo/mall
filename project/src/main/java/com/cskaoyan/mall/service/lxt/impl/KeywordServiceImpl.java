package com.cskaoyan.mall.service.lxt.impl;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.BrandExample;
import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.bean.KeywordExample;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.mapper.KeywordMapper;
import com.cskaoyan.mall.service.lxt.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordServiceImpl implements KeywordService {
    @Autowired
    KeywordMapper keywordMapper;
    @Override
    public List<Keyword> getKeywordList(int page, int limit, String sort, String order) {
        KeywordExample keywordExample = new KeywordExample();
        keywordExample.setOrderByClause(sort+" "+order);
        List<Keyword> list = keywordMapper.selectByExample(keywordExample);
        return list.subList(limit*(page-1),Math.min(limit*page,list.size()));
    }

    @Override
    public long countKeyword() {
        return keywordMapper.countByExample(new KeywordExample());
    }
}

package com.cskaoyan.mall.service.lxt.impl;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.mapper.KeywordMapper;
import com.cskaoyan.mall.service.lxt.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class KeywordServiceImpl implements KeywordService {
    @Autowired
    KeywordMapper keywordMapper;
    @Override
    public List<Keyword> getKeywordList(int page, int limit, String sort, String order,String keyword,String url) {
        KeywordExample keywordExample = new KeywordExample();
        keywordExample.setOrderByClause(sort+" "+order);
        if(keyword!=null){
            keywordExample.createCriteria().andKeywordLike("%"+keyword+"%");
        }
        if(url!=null){
            keywordExample.createCriteria().andUrlLike("%"+url+"%");
        }
        List<Keyword> list = keywordMapper.selectByExample(keywordExample);
        return list.subList(limit*(page-1),Math.min(limit*page,list.size()));
    }

    @Override
    public long countKeyword() {
        return keywordMapper.countByExample(new KeywordExample());
    }
    @Override
    public Keyword create(Keyword keyword) {
        keyword.setDeleted(false);
        Date date = new Date();
        keyword.setAddTime(date);
        keyword.setUpdateTime(date);
        keyword.setSortOrder(100);
        keywordMapper.insert(keyword);
        return keyword;
    }

    @Override
    public void delete(Integer id) {
        keywordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Keyword update(Keyword keyword) {
        Date date = new Date();
        keyword.setUpdateTime(date);
        keywordMapper.updateByPrimaryKey(keyword);
        return keyword;
    }
}

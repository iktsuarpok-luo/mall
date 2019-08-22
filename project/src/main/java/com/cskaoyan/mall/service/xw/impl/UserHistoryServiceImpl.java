package com.cskaoyan.mall.service.xw.impl;

import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.bean.FootprintExample;
import com.cskaoyan.mall.bean.Searchhistory;
import com.cskaoyan.mall.bean.SearchhistoryExample;
import com.cskaoyan.mall.mapper.FootprintMapper;
import com.cskaoyan.mall.mapper.SearchhistoryMapper;
import com.cskaoyan.mall.service.xw.UserHistoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHistoryServiceImpl implements UserHistoryService {
    @Autowired
    SearchhistoryMapper searchhistoryMapper;
    @Override
    public List<Searchhistory> queryHistory(int page, int limit, Integer userId, String keyword) {
        SearchhistoryExample searchhistoryExample = new SearchhistoryExample();
        PageHelper.startPage(page,limit);
        if(userId == null || userId.equals("")){
            userId = null;
        }
        if(keyword == null || keyword.equals("")){
            keyword = null;
        }
        if(userId == null && keyword == null){
            List<Searchhistory> searchhistoryList = searchhistoryMapper.selectByExample(searchhistoryExample);
            return searchhistoryList;
        }

        if(userId != null && keyword == null){
            searchhistoryExample.createCriteria().andUserIdEqualTo(userId);
            List<Searchhistory> searchhistoryList = searchhistoryMapper.selectByExample(searchhistoryExample);
            return searchhistoryList;
        }

        if(userId == null && keyword != null){
            String s = "%" + keyword +"%";
            searchhistoryExample.createCriteria().andKeywordLike(s);
            List<Searchhistory> searchhistoryList = searchhistoryMapper.selectByExample(searchhistoryExample);
            return searchhistoryList;
        }

        if(userId != null && keyword != null){
            String s = "%" + keyword +"%";
            searchhistoryExample.createCriteria().andKeywordLike(s).andUserIdEqualTo(userId);
            List<Searchhistory> searchhistoryList = searchhistoryMapper.selectByExample(searchhistoryExample);
            return searchhistoryList;
        }
        return null;
    }
}

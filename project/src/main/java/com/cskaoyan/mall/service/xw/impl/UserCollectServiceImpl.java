package com.cskaoyan.mall.service.xw.impl;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.bean.CollectExample;
import com.cskaoyan.mall.mapper.CollectMapper;
import com.cskaoyan.mall.service.xw.UserCollectService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCollectServiceImpl implements UserCollectService {
    @Autowired
    CollectMapper collectMapper;
    @Override
    public List<Collect> queryCollect(int page, int limit, Integer userId, Integer valueId) {
        CollectExample collectExample = new CollectExample();
        PageHelper.startPage(page,limit);
        if(userId == null || userId.equals("")){
            userId = null;
        }
        if(valueId == null || valueId.equals("")){
            valueId = null;
        }
        if(userId == null && valueId == null){
            List<Collect> collectList = collectMapper.selectByExample(collectExample);
            return collectList;
        }

        if(userId != null && valueId == null){
            collectExample.createCriteria().andUserIdEqualTo(userId);
            List<Collect> collectList = collectMapper.selectByExample(collectExample);
            return collectList;
        }

        if(userId == null && valueId != null){
            collectExample.createCriteria().andValueIdEqualTo(valueId);
            List<Collect> collectList = collectMapper.selectByExample(collectExample);
            return collectList;
        }

        if(userId != null && valueId != null){
            collectExample.createCriteria().andValueIdEqualTo(valueId).andUserIdEqualTo(userId);
            List<Collect> collectList = collectMapper.selectByExample(collectExample);
            return collectList;
        }
        return null;
    }
}

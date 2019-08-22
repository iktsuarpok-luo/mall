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

    @Override
    public int count(Integer id, int type) {
        CollectExample collectExample = new CollectExample();
        collectExample.createCriteria().andUserIdEqualTo(id);
        collectExample.createCriteria().andTypeEqualTo((byte) type);
        return (int)collectMapper.countByExample(collectExample);
    }

    @Override
    public int check(Integer goodsId, Integer userId, int type) {
        CollectExample collectExample = new CollectExample();
        collectExample.createCriteria().andValueIdEqualTo(goodsId);
        collectExample.createCriteria().andTypeEqualTo((byte) type);
        collectExample.createCriteria().andUserIdEqualTo(userId);
        return (int) collectMapper.countByExample(collectExample);
    }

    @Override
    public Collect addCollect(Collect collect) {
        collectMapper.insert(collect);
        return collect;
    }

    @Override
    public Collect getCollect(Integer userId, Byte type, Integer valueId) {
        CollectExample collectExample = new CollectExample();
        collectExample.createCriteria().andUserIdEqualTo(userId);
        collectExample.createCriteria().andTypeEqualTo(type);
        collectExample.createCriteria().andValueIdEqualTo(valueId);
        List<Collect> collects = collectMapper.selectByExample(collectExample);
        if(collects.size()==0){
            return null;
        }else {
            return collects.get(0);
        }
    }

    @Override
    public void delete(Collect collect) {
        collectMapper.deleteByPrimaryKey(collect.getId());
    }

    @Override
    public List<Collect> myCollect(int page, int limit, Integer userId, int type) {
        CollectExample collectExample = new CollectExample();
        PageHelper.startPage(page,limit);
        collectExample.createCriteria().andTypeEqualTo((byte) type).andUserIdEqualTo(userId);
        List<Collect> collectList = collectMapper.selectByExample(collectExample);
        return collectList;
    }
}

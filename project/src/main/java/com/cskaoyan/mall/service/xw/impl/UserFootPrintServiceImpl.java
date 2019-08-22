package com.cskaoyan.mall.service.xw.impl;

import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.bean.FootprintExample;
import com.cskaoyan.mall.mapper.FootprintMapper;
import com.cskaoyan.mall.service.xw.UserFootPrintService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFootPrintServiceImpl implements UserFootPrintService {
    @Autowired
    FootprintMapper footprintMapper;
    @Override
    public List<Footprint> queryFootPrint(int page, int limit, Integer userId, Integer goodsId) {
        FootprintExample footprintExample = new FootprintExample();
        PageHelper.startPage(page,limit);
        if(userId == null || userId.equals("")){
            userId = null;
        }
        if(goodsId == null || goodsId.equals("")){
            goodsId = null;
        }
        if(userId == null && goodsId == null){
            List<Footprint> footprintList = footprintMapper.selectByExample(footprintExample);
            return footprintList;
        }

        if(userId != null && goodsId == null){
            footprintExample.createCriteria().andUserIdEqualTo(userId);
            List<Footprint> footprintList = footprintMapper.selectByExample(footprintExample);
            return footprintList;
        }

        if(userId == null && goodsId != null){
            footprintExample.createCriteria().andGoodsIdEqualTo(goodsId);
            List<Footprint> footprintList = footprintMapper.selectByExample(footprintExample);
            return footprintList;
        }

        if(userId != null && goodsId != null){
            footprintExample.createCriteria().andGoodsIdEqualTo(goodsId).andUserIdEqualTo(userId);
            List<Footprint> footprintList = footprintMapper.selectByExample(footprintExample);
            return footprintList;
        }
        return null;
    }

    @Override
    public int countFootprintById(Integer id) {
        FootprintExample footprintExample = new FootprintExample();
        footprintExample.createCriteria().andUserIdEqualTo(id);
        int l = (int)footprintMapper.countByExample(footprintExample);
        return l;
    }
}

package com.cskaoyan.mall.service.wj.impl;

import com.cskaoyan.mall.mapper.wj.StatMapper;
import com.cskaoyan.mall.service.wj.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wj
 * @Date 2019/8/16 19:50
 */
@Service
public class StatServiceImpl implements StatService {
    @Autowired
    StatMapper statMapper;


    @Override
    public List<Map> statUser() {
        return statMapper.statUser();
    }

    @Override
    public List<Map> statOrder() {
        return statMapper.statOrder();
    }

    @Override
    public List<Map> statGoods() {
        return statMapper.statGoods();
    }
}

package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.mapper.AdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ethan
 * @date 2019/8/16 11:45
 */
@Service
public class AdServiceImpl implements AdService{
    @Autowired
    private AdMapper adMapper;
    @Override
    public List<Ad> selectList(String sort, String order) {
        return adMapper.selectByOrderAndSort(sort,order);
    }

    @Override
    public int add(Ad ad) {
        return adMapper.insert(ad);
    }
}

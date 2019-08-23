package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.AdExample;
import com.cskaoyan.mall.mapper.AdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public List<Ad> selectList(String name, String content) {
        AdExample adExample = new AdExample();
        if (name!=null&&content!=null){
            return adMapper.selectByNameAndContent(name,content);
        }else if (name!=null){
            adExample.createCriteria().andNameLike("%"+name+"%");
            return adMapper.selectByExample(adExample);
        }else if (content!=null){
            adExample.createCriteria().andNameLike("%"+content+"%");
            return adMapper.selectByExample(adExample);
        }else {
            return adMapper.selectByExample(adExample);
        }

    }

    @Override
    public int add(Ad ad) {
        ad.setAddTime(LocalDateTime.now());
        ad.setUpdateTime(LocalDateTime.now());
        return adMapper.insert(ad);
    }

    @Override
    public void delete(Ad ad) {
        Integer id = ad.getId();
        adMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Ad ad) {
        ad.setUpdateTime(LocalDateTime.now());
        return adMapper.updateByPrimaryKeySelective(ad);
    }

    @Override
    public Ad selectById(Integer id) {
        return adMapper.selectByPrimaryKey(id);
    }
}

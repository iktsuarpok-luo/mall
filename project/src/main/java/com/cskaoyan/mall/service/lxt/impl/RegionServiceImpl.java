package com.cskaoyan.mall.service.lxt.impl;

import com.cskaoyan.mall.bean.Region;
import com.cskaoyan.mall.bean.RegionExample;
import com.cskaoyan.mall.mapper.RegionMapper;
import com.cskaoyan.mall.service.lxt.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    RegionMapper regionMapper;
    @Override
    public List<Region> getRegionList() {
        RegionExample example = new RegionExample();
        example.createCriteria().andPidEqualTo(0);
        List<Region> list = regionMapper.selectByExample(example);
        list = getChildren(list);
        return list;
    }

    @Override
    public List<Region> getRegionListByPid(int pid) {
        RegionExample example = new RegionExample();
        example.createCriteria().andPidEqualTo(pid);
        return regionMapper.selectByExample(example);
    }

    private List<Region> getChildren(List<Region> list) {
        for (Region region : list) {
            RegionExample regionExample = new RegionExample();
            regionExample.createCriteria().andPidEqualTo(region.getId());
            List<Region> children = regionMapper.selectByExample(regionExample);

            if(children!=null && children.size()!=0){
                children=getChildren(children);
            }else{
                children=null;
            }
            region.setChildren(children);
        }
        return list;
    }
}

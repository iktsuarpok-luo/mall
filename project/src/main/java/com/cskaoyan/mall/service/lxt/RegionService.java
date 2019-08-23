package com.cskaoyan.mall.service.lxt;

import com.cskaoyan.mall.bean.Region;

import java.util.List;

public interface RegionService {
    List<Region> getRegionList();

    List<Region> getRegionListByPid(int pid);
}

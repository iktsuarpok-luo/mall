package com.cskaoyan.mall.service.lxt.impl;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.BrandExample;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.service.lxt.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandMapper brandMapper;
    @Override
    public List<Brand> getBrandList(int page, int limit, String sort, String order,String name,String id) {
        BrandExample brandExample = new BrandExample();
        brandExample.setOrderByClause(sort+" "+order);
        if(name!=null){
            brandExample.createCriteria().andNameLike("%"+name+"%");
        }
        if(id!=null&&id!=""){
            try {
                int searchId = Integer.parseInt(id);
                brandExample.createCriteria().andIdEqualTo(searchId);
            }catch (Exception e){
                brandExample.createCriteria().andIdEqualTo(null);
            }
        }
        List<Brand> list = brandMapper.selectByExample(brandExample);
        return list.subList(limit*(page-1),Math.min(limit*page,list.size()));
    }

    @Override
    public long countBrand() {
        return brandMapper.countByExample(new BrandExample());
    }
}

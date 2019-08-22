package com.cskaoyan.mall.service.lxt.impl;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.BrandExample;
import com.cskaoyan.mall.bean.StorageExample;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.mapper.StorageMapper;
import com.cskaoyan.mall.service.lxt.BrandService;
import com.cskaoyan.mall.service.lxt.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    StorageMapper storageMapper;
    @Autowired
    StorageService storageService;
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

    @Override
    public Brand create(Brand brand) {
        brandMapper.insert(brand);
        return brand;
    }

    @Override
    public Brand update(Brand brand) {
        Brand brand1 = brandMapper.selectByPrimaryKey(brand.getId());
        if (!brand1.getPicUrl().equals(brand.getPicUrl())) {
            storageService.deleteByUrl(brand1.getPicUrl());

            StorageExample storageExample = new StorageExample();
            storageExample.createCriteria().andUrlEqualTo(brand1.getPicUrl());
            storageMapper.deleteByExample(storageExample);
        }

        brandMapper.updateByPrimaryKey(brand);
        return brand;
    }

    @Override
    public void delete(Brand brand) {
        storageService.deleteByUrl(brand.getPicUrl());

        StorageExample storageExample = new StorageExample();
        storageExample.createCriteria().andUrlEqualTo(brand.getPicUrl());
        storageMapper.deleteByExample(storageExample);


        brandMapper.deleteByPrimaryKey(brand.getId());
    }

    @Override
    public Brand getBrandById(int id) {
        return brandMapper.selectByPrimaryKey(id);
    }
}

package com.cskaoyan.mall.service.zt;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.BrandExample;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.mapper.zt.GoodsBrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsBrandServiceImpl implements GoodsBrandService {

    @Autowired
    GoodsBrandMapper goodsBrandMapper;
    @Override
    public List<Brand> showBrandVauleAndLabel() {
        List<Brand> brandList = goodsBrandMapper.findBrandIdAndName();
        return brandList;
    }
}

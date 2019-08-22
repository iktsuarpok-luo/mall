package com.cskaoyan.mall.service.zt;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.mapper.zt.ZtBrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZtBrandServiceImpl implements ZtBrandService {

    @Autowired
    ZtBrandMapper goodsBrandMapper;
    @Override
    public List<Brand> showBrandVauleAndLabel() {
        List<Brand> brandList = goodsBrandMapper.findBrandIdAndName();
        return brandList;
    }
}

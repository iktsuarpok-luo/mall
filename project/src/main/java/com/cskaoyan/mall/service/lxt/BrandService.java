package com.cskaoyan.mall.service.lxt;

import com.cskaoyan.mall.bean.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getBrandList(int page, int limit, String sort, String order,String name,String id);

    long countBrand();

    Brand create(Brand brand);

    Brand update(Brand brand);

    void delete(Brand brand);
}

package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.service.lxt.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("admin/brand")
public class BrandController {
    @Autowired
    BrandService brandService;
    @RequestMapping("list")
    public BaseRespModel show(int page,int limit,String sort,String order,String name,String id){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            List<Brand> items = brandService.getBrandList(page,limit,sort,order,name,id);
            long total = brandService.countBrand();
            data.put("items",items);
            data.put("total",total);
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
    @RequestMapping("create")
    public BaseRespModel create(@RequestBody Brand brand){
        BaseRespModel resp = new BaseRespModel();
        try{
            brand.setDeleted(false);
            Date date = new Date();
            brand.setAddTime(date);
            brand.setUpdateTime(date);
            brand.setSortOrder((byte) 100);
            brand = brandService.create(brand);
            resp.setData(brand);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
    @RequestMapping("update")
    public BaseRespModel update(@RequestBody Brand brand){
        BaseRespModel resp = new BaseRespModel();
        try{
            Date date = new Date();
            brand.setUpdateTime(date);
            brand=brandService.update(brand);
            resp.setData(brand);
            resp.setErrmsg("成功");
            resp.setErrno(0);
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
    @RequestMapping("delete")
    public BaseRespModel delete(@RequestBody Brand brand){
        BaseRespModel resp = new BaseRespModel();
        try{
            brandService.delete(brand);

            resp.setErrmsg("成功");
            resp.setErrno(0);
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }

}

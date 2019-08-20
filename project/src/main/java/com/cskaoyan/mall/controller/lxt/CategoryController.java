package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.L1Category;
import com.cskaoyan.mall.service.lxt.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("admin/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @RequestMapping("list")
    public BaseRespModel show(){
        BaseRespModel resp = new BaseRespModel();
        try {
            List<Category> data = categoryService.getCategoryList();
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }

    @RequestMapping("l1")
    public BaseRespModel showl1(){
        BaseRespModel resp = new BaseRespModel();
        try {
            List<Category> list = categoryService.getFirstCategory();
            List<L1Category> data = new ArrayList();
            for (Category category : list) {
                L1Category l1Category = new L1Category(category.getName(),category.getId());
                data.add(l1Category);
            }
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
    public BaseRespModel create(@RequestBody Category category){
        BaseRespModel resp = new BaseRespModel();
        try{
            category=categoryService.add(category);
            resp.setData(category);
            resp.setErrmsg("成功");
            resp.setErrno(0);
        }catch (Exception e){
            resp.setErrmsg("失败");
            resp.setErrno(1);
        }
        return resp;
    }
    @RequestMapping("update")
    public BaseRespModel update(@RequestBody Category category){
        BaseRespModel resp = new BaseRespModel();
        try{
            category=categoryService.update(category);
            resp.setData(category);
            resp.setErrmsg("成功");
            resp.setErrno(0);
        }catch (Exception e){
            resp.setErrmsg("失败");
            resp.setErrno(1);
        }
        return resp;
    }
    @RequestMapping("delete")
    public BaseRespModel delete(@RequestBody Category category){
        BaseRespModel resp = new BaseRespModel();
        try{
            categoryService.delete(category);
            resp.setErrmsg("成功");
            resp.setErrno(0);
        }catch (Exception e){
            resp.setErrmsg("失败");
            resp.setErrno(1);
        }
        return resp;
    }
}

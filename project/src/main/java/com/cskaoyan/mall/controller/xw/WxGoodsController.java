package com.cskaoyan.mall.controller.xw;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.service.lxt.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
@RestController
@RequestMapping("wx/goods")
public class WxGoodsController {
    @Autowired
    CategoryService categoryService;
    @RequestMapping("count")
    public BaseRespModel GoodsCount(){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            data.put("goodsCount",0);
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
    @RequestMapping("category")
    public BaseRespModel category(int id){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            Category currentCategory = categoryService.getCurrentCategory(id);
            if ("L1".equals(currentCategory.getLevel())){
                currentCategory = categoryService.getSubCategory(currentCategory.getId()).get(0);
            }
            data.put("currentCategory",currentCategory);
            data.put("brotherCategory",categoryService.getSubCategory(currentCategory.getPid()));
            data.put("parentCategory",categoryService.getCurrentCategory(currentCategory.getPid()));
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrmsg("失败");
            resp.setErrno(1);
        }
        return resp;
    }
    @RequestMapping("list")
    public BaseRespModel Brand(Integer id,int page,int size){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            data.put("count",0);
            data.put("goodsList",new ArrayList<>());
            data.put("filterCategoryList",new ArrayList<>());
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrmsg("失败");
            resp.setErrno(1);
        }
        return resp;
    }
}

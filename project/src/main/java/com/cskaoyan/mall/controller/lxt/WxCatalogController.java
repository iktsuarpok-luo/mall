package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.service.lxt.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("wx/catalog")
public class WxCatalogController {
    @Autowired
    CategoryService categoryService;
    @RequestMapping("index")
    public BaseRespModel catalogShow(){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            data.put("categoryList",categoryService.getFirstCategory());
            data.put("currentCategory",categoryService.getCurrentCategory(1005000));
            data.put("currentSubCategory",categoryService.getSubCategory(1005000));
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrmsg("失败");
            resp.setErrno(1);
        }
        return resp;
    }
    @RequestMapping("current")
    public BaseRespModel current(int id){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            data.put("currentCategory",categoryService.getCurrentCategory(id));
            data.put("currentSubCategory",categoryService.getSubCategory(id));
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

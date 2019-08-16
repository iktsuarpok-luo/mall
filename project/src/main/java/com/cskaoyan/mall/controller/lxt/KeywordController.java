package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.service.lxt.BrandService;
import com.cskaoyan.mall.service.lxt.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("admin/keyword")
public class KeywordController {
    @Autowired
    KeywordService keywordService;
    @RequestMapping("list")
    public BaseRespModel show(int page, int limit, String sort, String order,String keyword,String url){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            List<Keyword> items = keywordService.getKeywordList(page,limit,sort,order,keyword,url);
            long total = keywordService.countKeyword();
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
    public BaseRespModel create(@RequestBody Keyword keyword){
        BaseRespModel resp = new BaseRespModel();
        try {
            Keyword data = keywordService.create(keyword);
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
    @RequestMapping("delete")
    public BaseRespModel delete(@RequestBody Keyword keyword){
        BaseRespModel resp = new BaseRespModel();
        try {
            keywordService.delete(keyword.getId());
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
    @RequestMapping("update")
    public BaseRespModel update(@RequestBody Keyword keyword){
        BaseRespModel resp = new BaseRespModel();
        try {
            Keyword data = keywordService.update(keyword);
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
}
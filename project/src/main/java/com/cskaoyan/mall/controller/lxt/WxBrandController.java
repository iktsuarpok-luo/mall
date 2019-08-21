package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.service.lxt.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("wx/brand")
public class WxBrandController {
    @Autowired
    BrandService brandService;
    @RequestMapping("detail")
    public BaseRespModel detail(int id){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            data.put("brand",brandService.getBrandById(id));
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

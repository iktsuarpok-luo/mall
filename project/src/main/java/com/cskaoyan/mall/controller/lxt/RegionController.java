package com.cskaoyan.mall.controller.lxt;


import com.cskaoyan.mall.bean.BaseRespModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/region")
public class RegionController {
    @RequestMapping("list")
    public BaseRespModel show(){
        BaseRespModel resp = new BaseRespModel();
        return resp;
    }
}

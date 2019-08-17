package com.cskaoyan.mall.controller.lxt;


import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Region;
import com.cskaoyan.mall.service.lxt.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/region")
public class RegionController {
    @Autowired
    RegionService regionService;
    @RequestMapping("list")
    public BaseRespModel show(){
        BaseRespModel<List<Region>> resp = new BaseRespModel();
        try {
            List<Region> data = regionService.getRegionList();
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

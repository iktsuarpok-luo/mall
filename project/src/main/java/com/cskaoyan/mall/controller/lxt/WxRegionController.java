package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.service.lxt.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/region")
public class WxRegionController {
    @Autowired
    RegionService regionService;
    @RequestMapping("list")
    public BaseRespModel list(int pid){
        BaseRespModel resp = new BaseRespModel();
        try {
            resp.setData(regionService.getRegionListByPid(pid));
            resp.setErrmsg("成功");
            resp.setErrno(0);
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
}

package com.cskaoyan.mall.controller.xw;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.bean.PageBean;
import com.cskaoyan.mall.service.xw.UserCollectService;
import com.cskaoyan.mall.service.xw.UserFootPrintService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class UserFootPrintController {
    @Autowired
    UserFootPrintService userFootPrintService;
    @RequestMapping("footprint/list")
    public BaseRespModel<PageBean> queryFootPrint(int page, int limit, Integer userId, Integer goodsId){
        BaseRespModel<PageBean> respModel = new BaseRespModel<>();
        try {
        List<Footprint> footprintList = userFootPrintService.queryFootPrint(page, limit, userId, goodsId);
        PageInfo<Footprint> pageInfo = new PageInfo<>(footprintList);
        PageBean<Footprint> pageBean = new PageBean<>(footprintList, pageInfo.getTotal());

        respModel.setData(pageBean);
        respModel.setErrmsg("成功");
        respModel.setErrno(0);}
        catch (Exception e){
            respModel.setErrno(1);
            respModel.setErrmsg("失败");
        }
        return respModel;
    }
}

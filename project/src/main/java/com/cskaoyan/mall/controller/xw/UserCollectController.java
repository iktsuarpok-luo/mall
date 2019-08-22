package com.cskaoyan.mall.controller.xw;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.bean.PageBean;
import com.cskaoyan.mall.service.xw.UserCollectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class UserCollectController {
    @Autowired
    UserCollectService userCollectService;
    @RequestMapping("collect/list")
    public BaseRespModel<PageBean> queryCollect(int page, int limit, Integer userId, Integer valueId){
        BaseRespModel<PageBean> respModel = new BaseRespModel<>();
        try{
        List<Collect> collectList = userCollectService.queryCollect(page, limit, userId, valueId);
        PageInfo<Collect> pageInfo = new PageInfo<>(collectList);
        PageBean<Collect> pageBean = new PageBean<>(collectList, pageInfo.getTotal());
        respModel.setData(pageBean);
        respModel.setErrmsg("成功");
        respModel.setErrno(0);
        }catch (Exception e){
            respModel.setErrno(1);
            respModel.setErrmsg("失败");
        }
        return respModel;
    }
}

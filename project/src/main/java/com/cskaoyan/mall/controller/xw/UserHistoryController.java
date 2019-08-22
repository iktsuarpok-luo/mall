package com.cskaoyan.mall.controller.xw;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.bean.PageBean;
import com.cskaoyan.mall.bean.Searchhistory;
import com.cskaoyan.mall.service.xw.UserFootPrintService;
import com.cskaoyan.mall.service.xw.impl.UserHistoryServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class UserHistoryController {
    @Autowired
    UserHistoryServiceImpl userHistoryService;
    @RequestMapping("history/list")
    public BaseRespModel<PageBean> queryHistory(int page, int limit, Integer userId, String keyword){
        List<Searchhistory> searchhistoryList = userHistoryService.queryHistory(page, limit, userId, keyword);
        PageInfo<Searchhistory> pageInfo = new PageInfo<>(searchhistoryList);
        PageBean<Searchhistory> pageBean = new PageBean<>(searchhistoryList, pageInfo.getTotal());
        BaseRespModel<PageBean> respModel = new BaseRespModel<>();
        respModel.setData(pageBean);
        respModel.setErrmsg("成功");
        respModel.setErrno(0);
        return respModel;
    }
}

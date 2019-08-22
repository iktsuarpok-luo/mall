package com.cskaoyan.mall.controller.wj;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.UserExample;
import com.cskaoyan.mall.bean.wj.StatModel;
import com.cskaoyan.mall.service.wj.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wj
 * @Date 2019/8/16 19:48
 */
@RestController
@RequestMapping("admin/stat")
public class StatController {
    @Autowired
    StatService statService;

    @RequestMapping("user")
    public BaseRespModel userStat() {
        BaseRespModel<StatModel> statModelBaseRespModel = new BaseRespModel<>();

        StatModel statModel = new StatModel();
        statModel.setColumns(new String[]{"day","users"});
        List<Map> rows = statService.statUser();
        statModel.setRows(rows);

        statModelBaseRespModel.setData(statModel);
        statModelBaseRespModel.setErrmsg("成功");
        statModelBaseRespModel.setErrno(0);
        return  statModelBaseRespModel;
    }


    @RequestMapping("order")
    public BaseRespModel orderStat(){
        BaseRespModel<StatModel> statModelBaseRespModel = new BaseRespModel<>();

        StatModel statModel = new StatModel();
        statModel.setColumns(new String[]{"day", "orders", "customers", "amount", "pcr"});
        List<Map> rows = statService.statOrder();
        statModel.setRows(rows);

        statModelBaseRespModel.setData(statModel);
        statModelBaseRespModel.setErrmsg("成功");
        statModelBaseRespModel.setErrno(0);
        return  statModelBaseRespModel;
    }

    @RequestMapping("goods")
    public BaseRespModel goodsStat(){
        BaseRespModel<StatModel> statModelBaseRespModel = new BaseRespModel<>();

        StatModel statModel = new StatModel();
        statModel.setColumns(new String[]{"day", "orders", "products", "amount"});
        List<Map> rows = statService.statGoods();
        statModel.setRows(rows);

        statModelBaseRespModel.setData(statModel);
        statModelBaseRespModel.setErrmsg("成功");
        statModelBaseRespModel.setErrno(0);
        return  statModelBaseRespModel;
    }

}

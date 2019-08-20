package com.cskaoyan.mall.controller.wj;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.service.wj.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wj
 * @Date 2019/8/16 15:37
 */

@RestController
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @RequestMapping("admin/dashboard")
    public BaseRespModel dashboard(){
        BaseRespModel<Map> mapBaseRespModel = new BaseRespModel<>();

        UserExample userExample = new UserExample();
        GoodsExample goodsExample = new GoodsExample();
        GoodsproductExample goodsproductExample = new GoodsproductExample();
        OrderExample orderExample = new OrderExample();

        HashMap<String, Object> data= new HashMap<>();
        data.put("goodsTotal", dashboardService.countByExample(goodsExample));
        data.put("orderTotal", dashboardService.countByExample(orderExample));
        data.put("productTotal", dashboardService.countByExample(goodsproductExample));
        data.put("userTotal", dashboardService.countByExample(userExample));

        mapBaseRespModel.setData(data);
        mapBaseRespModel.setErrmsg("成功");
        mapBaseRespModel.setErrno(0);

        return  mapBaseRespModel;
    }
}


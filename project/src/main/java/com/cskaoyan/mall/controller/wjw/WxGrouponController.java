package com.cskaoyan.mall.controller.wjw;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.Grouponrules;
import com.cskaoyan.mall.service.wjw.GroupRuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author ethan
 * @date 2019/8/21 23:33
 */
@RestController
@RequestMapping("wx/groupon")
public class WxGrouponController {
    @Autowired
    GroupRuleService groupRuleService;
    @RequestMapping("list")
    public BaseRespModel getList(Integer page,Integer size){
        PageHelper.startPage(page,size);
        List<Grouponrules> couponRulesList = groupRuleService.getList(page,size);
        BaseRespModel<HashMap> baseRespModel = new BaseRespModel<>();
        HashMap<String, Object> data = new HashMap<>();
        PageInfo<Grouponrules> pageInfo = new PageInfo<>(couponRulesList);
        long total = pageInfo.getTotal();
        data.put("count",total);
        data.put("data",couponRulesList);
        baseRespModel.setErrno(0);
        baseRespModel.setErrmsg("成功");
        baseRespModel.setData(data);
        return baseRespModel;
    }
}

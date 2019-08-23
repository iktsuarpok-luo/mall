package com.cskaoyan.mall.controller.wjw;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.Grouponrules;
import com.cskaoyan.mall.service.wjw.CouponService;
import com.cskaoyan.mall.service.wjw.GroupRuleService;
import com.cskaoyan.mall.service.zt.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ethan
 * @date 2019/8/21 23:33
 */
@RestController
@RequestMapping("wx/groupon")
public class WxGrouponController {
    @Autowired
    GroupRuleService groupRuleService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    CouponService couponService;

    @RequestMapping("list")
    public BaseRespModel getList(Integer page,Integer size){
        PageHelper.startPage(page,size);
        List<Grouponrules> grouponrulesList = groupRuleService.getList(null,null,null);
        List<Map<String, Object>> grouponData = new ArrayList<>();
        for (Grouponrules grouponrules : grouponrulesList) {
            HashMap<String, Object> rowData = new HashMap<>();
            Integer grouponMember = grouponrules.getDiscountMember();
            Integer goodsId = grouponrules.getGoodsId();
            Goods goods = goodsService.findGoodsById(goodsId);
            rowData.put("goods",goods);
            int discount = grouponrules.getDiscount();
            int grouponPrice= goods.getCounterPrice()-discount;
            rowData.put("groupon_member",grouponMember);
            rowData.put("groupon_price",grouponPrice);
            grouponData.add(rowData);
        }
        BaseRespModel<HashMap> baseRespModel = new BaseRespModel<>();
        HashMap<String, Object> data = new HashMap<>();
        PageInfo<Grouponrules> pageInfo = new PageInfo<>(grouponrulesList);
        long total = pageInfo.getTotal();
        data.put("count",total);
        data.put("data",grouponData);
        baseRespModel.setErrno(0);
        baseRespModel.setErrmsg("成功");
        baseRespModel.setData(data);
        return baseRespModel;
    }


}

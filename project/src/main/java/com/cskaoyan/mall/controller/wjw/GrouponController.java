package com.cskaoyan.mall.controller.wjw;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.Grouponrules;
import com.cskaoyan.mall.service.wjw.GoodsService;
import com.cskaoyan.mall.service.wjw.GroupRuleService;
import com.cskaoyan.mall.service.wjw.GrouponService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ethan
 * @date 2019/8/17 21:18
 */
@RestController
@RequestMapping("admin/groupon")
public class GrouponController {
    @Autowired
    GrouponService grouponService;

    @Autowired
    GroupRuleService groupRuleService;

    @Autowired
    GoodsService goodsService;
    /*团购规则显示及搜索*/
    @RequestMapping("list")
    public BaseRespModel getList(int page,int limit,String sort,String order,Integer goodsId){
        PageHelper.startPage(page,limit);
        BaseRespModel<Object> baseRespModel = new BaseRespModel<>();
        List<Grouponrules> grouponList= groupRuleService.getList(sort,order,goodsId);
        PageInfo<Grouponrules> pageInfo = new PageInfo<>(grouponList);
        long total = pageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("items",grouponList);
        baseRespModel.setData(map);
        baseRespModel.setErrmsg("成功");
        baseRespModel.setErrno(0);
        return baseRespModel;
    }
    /*团购显示及搜索*/
    @RequestMapping("listRecord")
    public BaseRespModel getListRecord(Integer page,Integer limit,String sort,String order,Integer goodsId){
        PageHelper.startPage(page,limit);
        //此处的goodsId就是grouponId
        BaseRespModel<HashMap> baseRespModel = new BaseRespModel<>();
        /*获得团队活动groupon相关list*/
        List<Groupon> grouponList = grouponService.getListRecord(sort,order,goodsId);

        List<Map<String, Object>> items = new ArrayList<>();
        for (Groupon groupon : grouponList) {
            HashMap<String, Object> rowData = new HashMap<>();
            rowData.put("groupon",groupon);
            List<Groupon> subgrouponList = grouponService.selectGrouponById(groupon.getId());
            rowData.put("subGroupons",subgrouponList);
            Grouponrules grouponrules = groupRuleService.selectRulesById(groupon.getRulesId());
            rowData.put("rules",grouponrules);
            Goods goods = goodsService.selectById(grouponrules.getGoodsId());
            rowData.put("goods",goods);
            items.add(rowData);
        }
        PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(items);
        long total = mapPageInfo.getTotal();
        HashMap<String, Object> data = new HashMap<>();
        data.put("total",total);
        data.put("items",items);
        baseRespModel.setData(data);
        baseRespModel.setErrno(0);
        baseRespModel.setErrmsg("成功");
        return baseRespModel;
    }
    /*添加功能*/
    @RequestMapping("create")
    public BaseRespModel add(@RequestBody Grouponrules grouponrules){
        BaseRespModel<Grouponrules> baseRespModel = new BaseRespModel<>();
        Integer goodsId = grouponrules.getGoodsId();
        Goods goods =goodsService.selectById(goodsId);
        grouponrules.setGoodsName(goods.getName());
        grouponrules.setPicUrl(goods.getPicUrl());
        grouponrules.setDeleted(false);
        int i = groupRuleService.add(grouponrules);
        if (i>0){
            baseRespModel.setErrmsg("成功");
            baseRespModel.setErrno(0);
            return baseRespModel;
        }else {
            baseRespModel.setErrmsg("参数错误");
            baseRespModel.setErrno(100);
            return baseRespModel;
        }
    }
    /*删除功能*/
    @RequestMapping("delete")
    public BaseRespModel delete(@RequestBody Grouponrules grouponrules){
        BaseRespModel<Grouponrules> baseRespModel = new BaseRespModel<>();
        int i = groupRuleService.delete(grouponrules);
        if (i>0){
            baseRespModel.setErrno(0);
            baseRespModel.setErrmsg("成功");
            return baseRespModel;
        }else {
            return null;
        }
    }
    /*修改功能*/
    @RequestMapping("update")
    public BaseRespModel update(@RequestBody Grouponrules grouponrules){
        BaseRespModel<Grouponrules> baseRespModel = new BaseRespModel<>();
        int i =groupRuleService.update(grouponrules);
        if (i>0){
            baseRespModel.setErrmsg("成功");
            baseRespModel.setErrno(0);
            return baseRespModel;
        }else {
            return null;
        }
    }

}

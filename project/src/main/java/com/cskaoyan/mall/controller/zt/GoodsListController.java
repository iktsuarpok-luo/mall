package com.cskaoyan.mall.controller.zt;


import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.service.zt.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("admin/goods/")
public class GoodsListController {

    @Autowired
    GoodsService goodsService;

    /**
     * 分页显示商品列表页面
     * 由于搜索框url与主页面url相同，需要在service层进一步判断搜索框是否有输入内容，据此写不同的查询接口
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("list")
    public BaseRespModel findAllGoods(int page,int limit,@Param("goodSn") String goodsSn, @Param("name") String name){

        //分页
        PageHelper.startPage(page,limit);//此方法后面的第一个查询语句按照此方法进行分页
        List<Goods> goods = goodsService.findAllGoodsOrByGoodsSnOrByName(goodsSn,name);//查询数据
        PageInfo goodsPageInfo = new PageInfo(goods);//查询结果封装到pageinfo上，获得更多页面信息

        //将查询的数据封装到map里面
        HashMap data = new HashMap();
        data.put("items",goods);
        data.put("total",goodsPageInfo.getTotal());//获取total

        BaseRespModel baseRespModel = new BaseRespModel();
        baseRespModel.setErrno(0);
        baseRespModel.setData(data);
        baseRespModel.setErrmsg("成功");
        return baseRespModel;
    }

    @RequestMapping("delete")
    public HashMap delete(Integer id){
        goodsService.delete(id);
        HashMap map = new HashMap<>();
        map.put("errno",0);
        map.put("errmsg","成功");
        return map;
    }


    /*@RequestMapping("list")
    public BaseRespModel findGoodsByGoodsSn(int page,int limit,String goodsSn){
        PageHelper.startPage(page,limit);//此方法后面的第一个查询语句按照此方法进行分页
        List<Goods> goods = goodsService.findGoodsByGoodsSn(goodsSn);//查询所有数据
        PageInfo goodsPageInfo = new PageInfo(goods);//查询结果封装到pageinfo上，获得更多页面信息

        //将查询的数据封装到map里面
        HashMap data = new HashMap();
        data.put("items",goods);
        data.put("total",goodsPageInfo.getTotal());//获取total

        BaseRespModel baseRespModel = new BaseRespModel();
        baseRespModel.setErrno(0);
        baseRespModel.setData(data);
        baseRespModel.setErrmsg("成功");
        return baseRespModel;
    }*/


    //不分页写法
    /*public HashMap findAllGoods(){
        //将goods的list集、total封装进名为data的map集
        List<Goods> goods = goodsService.findAllGoods();
        HashMap data = new HashMap<>();
        data.put("items",goods);
        data.put("total",goods.size());

        //将data、errno、errmsg封装进map集
        HashMap map = new HashMap<>();
        map.put("data",data);
        map.put("errno",0);
        map.put("errmsg","成功");
        return map;
    }*/


}

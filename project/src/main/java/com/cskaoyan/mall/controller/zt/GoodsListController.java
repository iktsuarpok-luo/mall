package com.cskaoyan.mall.controller.zt;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.zt.ZtCategory;
import com.cskaoyan.mall.service.lxt.CategoryService;
import com.cskaoyan.mall.service.zt.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.System;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/goods/")
public class GoodsListController {

    @Autowired
    GoodsService goodsService;
    @Autowired
    ZtBrandService ztBrandService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    GoodsattributeService goodsattributeService;
    @Autowired
    ZtcategoryService ztcategoryService;
    @Autowired
    GoodsproductService goodsproductService;
    @Autowired
    GoodsspecificationService goodsspecificationService;

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
    public HashMap delete(@RequestBody Goods goods){
        goodsService.delete(goods.getId());
        HashMap map = new HashMap<>();
        map.put("errno",0);
        map.put("errmsg","成功");
        return map;
    }

    @RequestMapping("catAndBrand")
    public BaseRespModel showCatgoryAndBrand(){

        List<Brand> brandList = ztBrandService.showBrandVauleAndLabel();
        List<ZtCategory> categoryList = ztcategoryService.showCategoryList();

        HashMap data = new HashMap();
        data.put("brandList",brandList);
        data.put("categoryList",categoryList);

        BaseRespModel baseRespModel = new BaseRespModel();
        baseRespModel.setErrno(0);
        baseRespModel.setData(data);
        baseRespModel.setErrmsg("成功");
        return baseRespModel;
    }

    @RequestMapping("detail")
    public BaseRespModel showDetail(@Param("id") int id){
        Goods goods = goodsService.findGoodsById(id);
        List<Goodsattribute> attributes = goodsattributeService.findGoodsattributesByGoodsId(id);
        List categoryIds = ztcategoryService.findcategoryIdsById(id);
        List<Goodsproduct> products = goodsproductService.findProductById(id);
        List<Goodsspecification> specifications = goodsspecificationService.findSpecificationById(id);

        HashMap data = new HashMap();
        data.put("attributes",attributes);
        data.put("categoryIds",categoryIds);
        data.put("goods",goods);
        data.put("products",products);
        data.put("specifications",specifications);

        BaseRespModel baseRespModel = new BaseRespModel();
        baseRespModel.setErrno(0);
        baseRespModel.setData(data);
        baseRespModel.setErrmsg("成功");
        return baseRespModel;
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public BaseRespModel update(@RequestBody JSONObject jsonObject){
        //解析json
        String data = jsonObject.toJSONString();
        JSONObject json = JSON.parseObject(data);

        //解析goods,封装进javabean
        String goodsStr = json.getString("goods");
        ObjectMapper objectMapper = new ObjectMapper();
        Goods goods = null;
        try {
            goods = objectMapper.readValue(goodsStr,Goods.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //attributues
        String attributesStr = json.getString("attributes");
        JSONArray attributesArray = JSONArray.parseArray(attributesStr);
        Goodsattribute[] attributes = new Goodsattribute[attributesArray.size()];
        for (int i = 0; i < attributesArray.size(); i++) {
            attributes[i] = JSONObject.toJavaObject((JSON) attributesArray.get(i),Goodsattribute.class);
        }
        //products
        String productsStr = json.getString("products");
        JSONArray productArr = JSONArray.parseArray(productsStr);
        Goodsproduct[] products = new Goodsproduct[productArr.size()];
        for(int i=0;i<productArr.size();i++){
            products[i]=null;
            try {
                products[i] = objectMapper.readValue(productArr.get(i).toString(), Goodsproduct.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //specifications
        String specificationStr = json.getString("specifications");
        JSONArray specificationArr = JSONArray.parseArray(specificationStr);
        Goodsspecification[] specifications = new Goodsspecification[specificationArr.size()];
        for(int i=0;i<specificationArr.size();i++){
            specifications[i]=null;
            try {
                specifications[i] = objectMapper.readValue(specificationArr.get(i).toString(), Goodsspecification.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        goodsService.update(goods,attributes,products,specifications);
        BaseRespModel baseRespModel = new BaseRespModel<>();
        baseRespModel.setErrno(0);
        baseRespModel.setErrmsg("成功");
        return baseRespModel;

    }
    @RequestMapping("create")
    public BaseRespModel creat(@RequestBody Goods goods){
        goods = goodsService.add(goods);
        BaseRespModel baseRespModel = new BaseRespModel();
        baseRespModel.setErrno(0);
        baseRespModel.setData(goods);
        baseRespModel.setErrmsg("成功");
        return baseRespModel;
    }

    /*@RequestMapping(value = "create", method = RequestMethod.POST)
    public Map<String,Object> creat(@RequestBody JSONObject jsonObject){
        String data = jsonObject.toString();
        //解析json数据
        JSONObject json = JSON.parseObject(data);

        //1. 解析Attributes，封装javabean
        String attributesStr = json.getString("attributes");
        JSONArray attributeArr = JSONArray.parseArray(attributesStr);
        Goodsattribute[] attributes = new Goodsattribute[attributeArr.size()];
        for (int i = 0; i < attributeArr.size(); i++) {
            attributes[i] = JSONObject.toJavaObject((JSON) attributeArr.get(i),Goodsattribute.class);
        }

        //2. 对goods进行解析
        String goodsStr = json.getString("goods");
        ObjectMapper objectMapper = new ObjectMapper();
        Goods goods = null;
        try {
            goods = objectMapper.readValue(goodsStr,Goods.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. 对products进行解析
        String productsStr = json.getString("products");
        JSONArray parseArray = JSONArray.parseArray(productsStr);
        Goodsproduct[] products = new Goodsproduct[parseArray.size()];
        for (int i = 0; i < parseArray.size(); i++) {
            products[i] = null;
            try {
                products[i] = objectMapper.readValue(parseArray.get(i).toString(),Goodsproduct.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 4. specifications 解析
        String specificationsStr = json.getString("specifications");
        JSONArray specificationArr = JSONArray.parseArray(specificationsStr);
        Goodsspecification[] specifications = new Goodsspecification[specificationArr.size()];
        for (int i = 0; i < specificationArr.size(); i++) {
            specifications[i] = null;
            try {
                specifications[i] = objectMapper.readValue(specificationArr.get(i).toString(), Goodsspecification.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        goodsService.create(goods);
        goodsattributeService.create(goods.getGoodsSn(),attributes);
        goodsproductService.create(goods.getGoodsSn(),products);
        goodsspecificationService.create(goods.getGoodsSn(),specifications);
        HashMap<String, Object> map = new HashMap<>();
        map.put("errmsg","成功");
        map.put("errno",0);

        return map;
    }*/

}

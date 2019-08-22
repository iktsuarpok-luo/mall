package com.cskaoyan.mall.controller.xw;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.service.lxt.BrandService;
import com.cskaoyan.mall.service.lxt.CategoryService;
import com.cskaoyan.mall.service.wjw.AdService;
import com.cskaoyan.mall.service.wjw.CouponService;
import com.cskaoyan.mall.service.wjw.GroupRuleService;
import com.cskaoyan.mall.service.wjw.GrouponService;
import com.cskaoyan.mall.service.zt.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("wx/home")
public class WxHomeController {
    @Autowired
    AdService adService;
    @Autowired
    BrandService brandService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CouponService couponService;
    @Autowired
    GroupRuleService groupRuleService;
    @Autowired
    GoodsService goodsService;

    @RequestMapping("index")
    public BaseRespModel homeShow(){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            data.put("banner",adService.selectList(null,null));
            data.put("brandList",brandService.getBrandList(1,4,"id","desc",null,null));
            data.put("channel",categoryService.getFirstCategory());
            data.put("couponList",couponService.selectLimitList(4,"id","desc"));

            List list = new ArrayList();
            List<Category> category = categoryService.getCategoryByPid();
            for (Category category1 : category) {
                HashMap hashMap = new HashMap();
                hashMap.put("id",category1.getId());
                hashMap.put("name",category1.getName());
                List<Category> subCategory = categoryService.getSubCategory(category1.getId());
                ArrayList arrayList = new ArrayList();
                for (Category category2 : subCategory) {
                    List<Goods> goodsList = goodsService.getFloorGoodsList(category2.getId());
                    arrayList.addAll(goodsList);
                }
                hashMap.put("goodsList",arrayList.subList(0,Math.min(6,arrayList.size())));
                list.add(hashMap);
            }
            data.put("floorGoodsList",list);//新建bean
            data.put("grouponList",groupRuleService.getLimitList(4,"id","desc"));//等
            data.put("hotGoodsList",goodsService.getHotGoodsList().subList(0,Math.min(6,goodsService.getHotGoodsList().size())));
            data.put("newGoodsList",goodsService.getNewGoodsList().subList(0,Math.min(6,goodsService.getNewGoodsList().size())));
            data.put("topicList",null);//肖旺写
            resp.setData(data);
            resp.setErrmsg("成功");
            resp.setErrno(0);
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }

}

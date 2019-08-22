package com.cskaoyan.mall.controller.xw;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.service.lxt.BrandService;
import com.cskaoyan.mall.service.lxt.CategoryService;
import com.cskaoyan.mall.service.lxt.IssueService;
import com.cskaoyan.mall.service.wjw.GroupRuleService;
import com.cskaoyan.mall.service.xw.UserCollectService;
import com.cskaoyan.mall.service.zt.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("wx/goods")
public class WxGoodsController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    GoodsattributeService goodsattributeService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    BrandService brandService;
    @Autowired
    CommentService commentService;
    @Autowired
    GroupRuleService groupRuleService;
    @Autowired
    IssueService issueService;
    @Autowired
    GoodsproductService goodsproductService;
    @Autowired
    GoodsspecificationService goodsspecificationService;
    @Autowired
    UserCollectService userCollectService;
    @RequestMapping("count")
    public BaseRespModel GoodsCount(){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            data.put("goodsCount",0);
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
    @RequestMapping("category")
    public BaseRespModel category(int id){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            Category currentCategory = categoryService.getCurrentCategory(id);
            if ("L1".equals(currentCategory.getLevel())){
                currentCategory = categoryService.getSubCategory(currentCategory.getId()).get(0);
            }
            data.put("currentCategory",currentCategory);
            data.put("brotherCategory",categoryService.getSubCategory(currentCategory.getPid()));
            data.put("parentCategory",categoryService.getCurrentCategory(currentCategory.getPid()));
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrmsg("失败");
            resp.setErrno(1);
        }
        return resp;
    }
    @RequestMapping("list")
    public BaseRespModel Brand(Integer id,int page,int size){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            data.put("count",0);
            data.put("goodsList",new ArrayList<>());
            data.put("filterCategoryList",new ArrayList<>());
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrmsg("失败");
            resp.setErrno(1);
        }
        return resp;
    }
    @RequestMapping("detail")
    public BaseRespModel detail(int id){
        BaseRespModel resp = new BaseRespModel();
        try{
            HashMap data = new HashMap();
            Goods goods = goodsService.findGoodsById(id);
            data.put("attribute",goodsattributeService.findGoodsattributesByGoodsId(id));
            data.put("brand",brandService.getBrandById(goods.getBrandId()));
            HashMap comment = new HashMap();
            comment.put("count",commentService.countComment(goods.getId(),0));
            comment.put("data",commentService.selectComments(goods.getId(),0));
            data.put("comment",comment);
            data.put("groupon",groupRuleService.getList(goods.getId()));
            data.put("info",goods);
            data.put("issue",issueService.getAllIssue());
            data.put("productList",goodsproductService.findProductById(goods.getId()));
            data.put("shareImg",goods.getShareUrl());//猜测
            List specificationList = new ArrayList();
            HashMap specification = new HashMap();
            specification.put("name","规格");
            specification.put("valueList",goodsspecificationService.findSpecificationByValueAndGoodsId(goods.getId(),(String) specification.get("name")));
            if (((List<Goodsspecification>)specification.get("valueList")).size()!=0){
                specificationList.add(specification);
            }
            specification.put("name","颜色");
            specification.put("valueList",goodsspecificationService.findSpecificationByValueAndGoodsId(goods.getId(),(String) specification.get("name")));
            if (((List<Goodsspecification>)specification.get("valueList")).size()!=0){
                specificationList.add(specification);
            }
            data.put("specificationList",specificationList);
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();
            data.put("userHasCollect",userCollectService.check(goods.getId(),user.getId(),0));
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
    @RequestMapping("related")
    public BaseRespModel related(int id){
        BaseRespModel resp = new BaseRespModel();
        try{
            HashMap data = new HashMap();
            Goods goods = goodsService.findGoodsById(id);
            List<Goods> list = goodsService.findRelatedGoods(goods);
            data.put("goodsList",list.subList(0,6));
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
}

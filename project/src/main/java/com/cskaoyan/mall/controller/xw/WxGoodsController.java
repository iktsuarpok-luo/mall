package com.cskaoyan.mall.controller.xw;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.service.lxt.BrandService;
import com.cskaoyan.mall.service.lxt.CategoryService;
import com.cskaoyan.mall.service.lxt.IssueService;
import com.cskaoyan.mall.service.wjw.GroupRuleService;
import com.cskaoyan.mall.service.xw.UserCollectService;
import com.cskaoyan.mall.service.xw.UserFootPrintService;
import com.cskaoyan.mall.service.zt.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    UserFootPrintService userFootPrintService;
    @RequestMapping("count")
    public BaseRespModel GoodsCount(){
        BaseRespModel resp = new BaseRespModel();
        try {
            HashMap data = new HashMap();
            data.put("goodsCount",goodsService.CountAllGoods());
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
    public BaseRespModel Brand(Integer categoryId,int page,int size,boolean isHot,boolean isNew,String order,String sort,String keyword) {
        BaseRespModel resp = new BaseRespModel();

        if (isHot == false && isNew == false && keyword == null) {                // 按照category类型二级列表显示，如：居家
            try {
                HashMap data = new HashMap();
                data.put("count", goodsService.count(categoryId));
                data.put("goodsList", goodsService.goodsList(categoryId));
                // filterCategoryList是干啥的？
                data.put("filterCategoryList",categoryService.filterCategoryList());

                resp.setData(data);
                resp.setErrno(0);
                resp.setErrmsg("成功");
            } catch (Exception e) {
                resp.setErrmsg("失败");
                resp.setErrno(1);
            }
        } else if (isNew == true) {             // 新品首发之综合
            try {
                HashMap data = new HashMap();
                data.put("count", goodsService.countByIsNew(isNew));
                List<Goods> list;
                if(categoryId==0) {
                    list = goodsService.goodsListByIsNew(isNew, order, sort);
                }else {
                    list = goodsService.goodsListByIsNewAndId(isNew, order, sort,categoryId);
                }
                data.put("goodsList", list);
                // filterCategoryList是干啥的？
                List<Category> categoryList = new ArrayList<>();
                List<Integer> ids = new ArrayList();
                List<Goods> filterList = goodsService.goodsListByIsNew(isNew, order, sort);
                for (Goods goods : filterList) {
                    int id = goods.getCategoryId();
                    if(!ids.contains(id)) {
                        ids.add(goods.getCategoryId());
                    }
                }
                for (int id : ids) {
                    categoryList.add(categoryService.getCurrentCategory(id));
                }
                data.put("filterCategoryList", categoryList);
//                data.put("filterCategoryList", null);

                resp.setData(data);
                resp.setErrno(0);
                resp.setErrmsg("成功");
            } catch (Exception e) {
                resp.setErrmsg("失败");
                resp.setErrno(1);
            }
        } else if (isHot == true) {
            try {
                HashMap data = new HashMap();
                data.put("count", goodsService.countByIsHot(isHot));
                List<Goods> list;
                if(categoryId==0) {
                    list = goodsService.goodsListByIsHot(isHot, order, sort);
                }else {
                    list = goodsService.goodsListByIsHotAndId(isHot, order, sort,categoryId);
                }
                data.put("goodsList", list);

                List<Category> categoryList = new ArrayList<>();
                List<Integer> ids = new ArrayList();
                List<Goods> filterList = goodsService.goodsListByIsHot(isHot, order, sort);
                for (Goods goods : filterList) {
                    int id = goods.getCategoryId();
                    if(!ids.contains(id)) {
                        ids.add(goods.getCategoryId());
                    }
                }
                for (int id : ids) {
                    categoryList.add(categoryService.getCurrentCategory(id));
                }
                data.put("filterCategoryList", categoryList);
//                data.put("filterCategoryList", null);

                resp.setData(data);
                resp.setErrno(0);
                resp.setErrmsg("成功");
            } catch (Exception e) {
                resp.setErrmsg("失败");
                resp.setErrno(1);
            }
        }

        if (keyword != null) {               // 模糊匹配查询
            try {
                HashMap data = new HashMap();
                data.put("count", goodsService.countByKeyword(keyword));

                List<Goods> list;
                if(categoryId==0) {
                    list = goodsService.goodsListByKeyword(keyword, order, sort);
                }else {
                    list = goodsService.goodsListByKeywordAndId(keyword, order, sort,categoryId);
                }
                data.put("goodsList", list);
                List<Category> categoryList = new ArrayList<>();
                List<Integer> ids = new ArrayList();
                List<Goods> filterList = goodsService.goodsListByKeyword(keyword, order, sort);
                for (Goods goods : filterList) {
                    int id = goods.getCategoryId();
                    if(!ids.contains(id)) {
                        ids.add(goods.getCategoryId());
                    }
                }
                for (int id : ids) {
                    categoryList.add(categoryService.getCurrentCategory(id));
                }
                data.put("filterCategoryList", categoryList);
//                data.put("filterCategoryList", null);

                resp.setData(data);
                resp.setErrno(0);
                resp.setErrmsg("成功");
            } catch (Exception e) {
                resp.setErrmsg("失败");
                resp.setErrno(1);
            }
        }

        return resp;
    }
    @RequestMapping("detail")
    public BaseRespModel detail(int id){
        BaseRespModel resp = new BaseRespModel();
        try{
            HashMap data = new HashMap();
            Goods goods = goodsService.findGoodsById(id);
            data.put("attribute",goodsattributeService.findGoodsattributesByGoods(id));
            data.put("brand",brandService.getBrandById(goods.getBrandId()));
            data.put("groupon",groupRuleService.getList(goods.getId()));
            data.put("info",goods);
            data.put("issue",issueService.getAllIssue());
            data.put("shareImg",goods.getShareUrl());//猜测
            List specificationList = new ArrayList();
            HashMap specification = new HashMap();
            specification.put("name","规格");
            specification.put("valueList",goodsspecificationService.findSpecificationByValueAndGoodsId(goods.getId(),(String) specification.get("name")));
            if (((List<Goodsspecification>)specification.get("valueList")).size()!=0){
                specificationList.add(specification);
            }
            HashMap specification1 = new HashMap();
            specification1.put("name","颜色");
            specification1.put("valueList",goodsspecificationService.findSpecificationByValueAndGoodsId(goods.getId(),(String) specification1.get("name")));
            if (((List<Goodsspecification>)specification1.get("valueList")).size()!=0){
                specificationList.add(specification1);
            }
            data.put("productList",goodsproductService.findProductById(goods.getId()));
            data.put("specificationList",specificationList);
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();
            if(user!=null){
                HashMap comment = new HashMap();
                comment.put("count",commentService.countComment(goods.getId(),0));
                List<Comment> comments = commentService.selectComments(goods.getId(), 0);
                comment.put("data",comments.subList(0,Math.min(3,comments.size())));
                data.put("comment",comment);
                data.put("userHasCollect",userCollectService.check(goods.getId(),user.getId(),0));
            }
            resp.setData(data);
            resp.setErrno(0);
            resp.setErrmsg("成功");
            //显示详情之后加入浏览足迹
            Date addTime = new Date();
            Footprint footprint = new Footprint();
            footprint.setUserId(user.getId());
            footprint.setGoodsId(id);
            footprint.setAddTime(addTime);
            footprint.setUpdateTime(addTime);
            footprint.setDeleted(false);
            userFootPrintService.addFootPrint(footprint);
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

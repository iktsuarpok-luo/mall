package com.cskaoyan.mall.service.zt;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.lxt.StorageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.Attribute;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsproductMapper goodsproductMapper;
    @Autowired
    GoodsspecificationMapper goodsspecificationMapper;
    @Autowired
    GoodsattributeMapper goodsattributeMapper;
    @Autowired
    StorageService storageService;
    @Autowired
    StorageMapper storageMapper;


    @Override
    public List<Goods> findAllGoodsOrByGoodsSnOrByName(@Param("goodSn") String goodsSn, @Param("name") String name) {
        List<Goods> goods = null;
        if ((goodsSn == null && goodsSn != ("")) && (name == null && name != (""))) {
            GoodsExample goodsExample = new GoodsExample();
            goodsExample.createCriteria().andIdIsNotNull();
            goods = goodsMapper.selectByExample(goodsExample);//搜索全部商品
        }else if(goodsSn!=null){
            goods = goodsMapper.findGoodsByGoodsSn(goodsSn);//根据输入的商品号全名搜索
        }else if (name!=null){
            goods = goodsMapper.findGoodsByNameMuti(name);//根据输入的商品名模糊搜索
        }
        return goods;
    }

    @Override
    public void delete(Integer id) {
        goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Goods findGoodsById(int id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public Goods add(Goods goods) {
        goodsMapper.insert(goods);
        return goods;
    }

    @Override
    public void update(Goods goods, Goodsattribute[] attributes,Goodsproduct[] goodsproducts,Goodsspecification[] goodsspecifications){

        Integer goodsId = goods.getId();
        goodsMapper.updateByPrimaryKey(goods);

        GoodsspecificationExample goodsspecificationExample = new GoodsspecificationExample();
        goodsspecificationExample.createCriteria().andGoodsIdEqualTo(goodsId);
        List<Goodsspecification> specificationList = goodsspecificationMapper.selectByExample(goodsspecificationExample);
        List<Integer> specificationIds = new ArrayList<>();
        for(Goodsspecification goodsspecification:specificationList){
            specificationIds.add(goodsspecification.getId());
        }
        for(Goodsspecification specification:goodsspecifications){
            if(specification.getId()==null){
                specification.setGoodsId(goodsId);
                specification.setDeleted(false);
                goodsspecificationMapper.insert(specification);
            }
        }
        List<Integer> specIds = new ArrayList<>();
        for(Goodsspecification specification:goodsspecifications){
            specIds.add(specification.getId());
        }
        for(Integer specificationId:specificationIds){
            if(!specIds.contains(specificationId)){
                Goodsspecification spec = goodsspecificationMapper.selectByPrimaryKey(specificationId);
                spec.setDeleted(true);
                goodsspecificationMapper.deleteByPrimaryKey(spec.getId());
            }
        }

        GoodsattributeExample goodsattributeExample = new GoodsattributeExample();
        goodsattributeExample.createCriteria().andGoodsIdEqualTo(goodsId);
        List<Goodsattribute> goodsattributesList =  goodsattributeMapper.selectByExample(goodsattributeExample);
        List<Integer> goodsattributesIds = new ArrayList<>();
        for (Goodsattribute goodsattribute:goodsattributesList){
             goodsattributesIds.add(goodsattribute.getId());
        }
        for(Goodsattribute goodsattribute:attributes){
            if(goodsattribute.getId()==null){
                goodsattribute.setGoodsId(goodsId);
                goodsattribute.setDeleted(false);
                goodsattributeMapper.insert(goodsattribute);
            }
        }

        List<Integer> attrbuteIds = new ArrayList<>();
        for(Goodsattribute goodsattribute:attributes){
            attrbuteIds.add(goodsattribute.getId());
        }
        for(Integer attributeId:goodsattributesIds){
            if(!attrbuteIds.contains(attributeId)){
                Goodsattribute goodsattribute = goodsattributeMapper.selectByPrimaryKey(attributeId);
                goodsattribute.setDeleted(true);
                //goodsattributeMapper.updateByPrimaryKey(goodsattribute);
                goodsattributeMapper.deleteByPrimaryKey(goodsattribute.getId());
            }
        }

        for(Goodsproduct goodsproduct :goodsproducts){
            goodsproductMapper.updateByPrimaryKey(goodsproduct);
        }

    }

    @Override
    public void create(Goods goods) {
        //添加商品时需要将其deleted字段设置为false
        goods.setDeleted(false);
        goods.setId(new Integer(goods.getGoodsSn()));
        goodsMapper.insert(goods);
    }

    @Override
    public List<Goods> findRelatedGoods(Goods goods) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andCategoryIdEqualTo(goods.getCategoryId());
        List<Goods> result = goodsMapper.selectByExample(goodsExample);
        if(result.size()<6){
            GoodsExample goodsExample2 = new GoodsExample();
            goodsExample2.createCriteria().andBrandIdEqualTo(goods.getBrandId());
            result.addAll(goodsMapper.selectByExample(goodsExample2));
        }
        return result;
    }

    // 热销商品
    @Override
    public List<Goods> getHotGoodsList() {
        List<Goods> goods = null;
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIsHotEqualTo(true);
        goods = goodsMapper.selectByExample(goodsExample);
        return goods;
    }

    // 最新上市商品
    @Override
    public List<Goods> getNewGoodsList() {
        List<Goods> goods = null;
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIsNewEqualTo(true);
        goods = goodsMapper.selectByExample(goodsExample);

        return goods;
    }

    @Override
    public List<Goods> getFloorGoodsList(int id) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andCategoryIdEqualTo(id);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods;
    }

    @Override
    public List<Goods> goodsList(int id) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andCategoryIdEqualTo(id);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods;
    }

    @Override
    public int count(int id) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andCategoryIdEqualTo(id);
        int count = ((int) goodsMapper.countByExample(goodsExample));
        return count;
    }

    @Override
    public int countByIsNew(boolean isNew) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIsNewEqualTo(isNew);
        int count = ((int) goodsMapper.countByExample(goodsExample));
        return count;
    }

    @Override
    public List<Goods> goodsListByIsNew(boolean isNew,String order,String sort) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIsNewEqualTo(isNew);
        goodsExample.setOrderByClause(sort + " " + order);

        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods;
    }

    @Override
    public int countByIsHot(boolean isHot) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIsHotEqualTo(isHot);
        int count = ((int) goodsMapper.countByExample(goodsExample));
        return count;
    }

    @Override
    public List<Goods> goodsListByIsHot(boolean isHot, String order, String sort) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIsHotEqualTo(isHot);
        goodsExample.setOrderByClause(sort + " " + order);

        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods;
    }

    @Override
    public List<Goods> goodsListByIsHotAndId(boolean isHot, String order, String sort, Integer categoryId) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIsHotEqualTo(isHot).andCategoryIdEqualTo(categoryId);
        goodsExample.setOrderByClause(sort + " " + order);

        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods;
    }

    @Override
    public List<Goods> goodsListByIsNewAndId(boolean isNew, String order, String sort, Integer categoryId) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIsNewEqualTo(isNew).andCategoryIdEqualTo(categoryId);
        goodsExample.setOrderByClause(sort + " " + order);

        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods;
    }

    @Override
    public int CountAllGoods() {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andGoodsSnIsNotNull();
        int count = ((int) goodsMapper.countByExample(goodsExample));
        return count;
    }

    @Override
    public int countByKeyword(String keyword) {
        GoodsExample goodsExample = new GoodsExample();
        String keywodLike = "%" + keyword + "%";
        goodsExample.createCriteria().andNameLike(keywodLike);
        int count = ((int) goodsMapper.countByExample(goodsExample));
        return count;
    }

    @Override
    public List<Goods> goodsListByKeyword(String keyword, String order, String sort) {
        GoodsExample goodsExample = new GoodsExample();
        String keywordLike = "%" + keyword + "%";
        goodsExample.createCriteria().andNameLike(keywordLike);
        goodsExample.setOrderByClause(sort + " " + order);

        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods;
    }

    @Override
    public List<Goods> goodsListByKeywordAndId(String keyword, String order, String sort, Integer categoryId) {
        GoodsExample goodsExample = new GoodsExample();
        String keywordLike = "%" + keyword + "%";
        goodsExample.createCriteria().andNameLike(keywordLike).andCategoryIdEqualTo(categoryId);
        goodsExample.setOrderByClause(sort + " " + order);

        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods;
    }
}



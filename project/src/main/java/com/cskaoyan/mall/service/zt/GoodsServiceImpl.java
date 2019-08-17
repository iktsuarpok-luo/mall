package com.cskaoyan.mall.service.zt;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsExample;
import com.cskaoyan.mall.bean.PageBean;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    /*@Override
    public List<Goods> findAllGoods() {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdIsNotNull();
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods;
    }

    @Override
    public List<Goods> findGoodsByGoodsSn(String goodsSn) {
        List<Goods> goods = goodsMapper.findGoodsByGoodsSn(goodsSn);
        return goods;
    }*/

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
        goodsMapper.deleteById(id);
    }
}

    //未分页
    /*@Override
    public List<Goods> findAllGoods() {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdIsNotNull();
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods;
    }*/

    /*//将数据库查询的结果封装到分页bean里
    @Override
    public PageBean<Goods> findAllGoods(int page,int rows) {
        PageHelper.startPage(page,rows);
        List<Goods> goods = goodsMapper.findAllGoods();
        PageBean pageBean = new PageBean();
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);
        pageBean.setRows(goods);
        pageBean.setTotal(pageInfo.getTotal());
        return pageBean;
    }*/


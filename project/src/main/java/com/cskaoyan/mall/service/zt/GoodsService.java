package com.cskaoyan.mall.service.zt;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsService {

    List<Goods> findAllGoodsOrByGoodsSnOrByName(String goodSn,String name);

    void delete(Integer id);

}

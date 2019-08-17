package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.Ad;

import java.util.List;
/**
 * @author ethan
 * @date 2019/8/16 11:44
 */
public interface AdService {
    List<Ad> selectList(String sort, String order);

    int add(Ad ad);

}

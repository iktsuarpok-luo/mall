package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.Ad;

import java.util.List;
/**
 * @author ethan
 * @date 2019/8/16 11:44
 */
public interface AdService {

    int add(Ad ad);

    void delete(Ad ad);

    int update(Ad ad);


    List<Ad> selectList(String name, String content);
}

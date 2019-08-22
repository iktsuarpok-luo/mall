package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.service.xw.UserCollectService;
import com.cskaoyan.mall.service.zt.GoodsService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("wx/collect")
public class WxCollectController {
    @Autowired
    UserCollectService collectService;
    @Autowired
    GoodsService goodsService;
    @RequestMapping("list")
    public BaseRespModel list(int type,int page,int size){
        BaseRespModel resp = new BaseRespModel();
        try{
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();
            HashMap data = new HashMap();
            List<Collect> collects = collectService.myCollect(page,size,user.getId(),type);
            List<Goods> list = new ArrayList<>();
            for (Collect collect : collects) {
                list.add(goodsService.findGoodsById(collect.getValueId()));
            }
            data.put("collectList",list);
            data.put("totalPages",Math.ceil(collectService.count(user.getId(),type)/size));
            resp.setData(data);
            resp.setErrmsg("成功");
            resp.setErrno(0);
        }catch (Exception e){
            resp.setErrmsg("失败");
            resp.setErrno(1);
        }
        return resp;
    }
    @RequestMapping("addordelete")
    public BaseRespModel addordelete(@RequestBody Collect collect){
        BaseRespModel resp = new BaseRespModel();
        try {
            Date date = new Date();
            collect.setAddTime(date);
            collect.setUpdateTime(date);
            collect.setDeleted(false);
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();
            collect.setUserId(user.getId());
            Collect flag = collectService.getCollect(collect.getUserId(),collect.getType(),collect.getValueId());
            if(flag!=null){
                collectService.delete(collect);
                resp.setData(flag);
            }else {
                collect = collectService.addCollect(collect);
                resp.setData(collect);
            }
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }
}

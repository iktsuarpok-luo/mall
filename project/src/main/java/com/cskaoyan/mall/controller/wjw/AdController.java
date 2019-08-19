package com.cskaoyan.mall.controller.wjw;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.service.wjw.AdService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author ethan
 * @date 2019/8/16 9:02
 */
@RestController
@RequestMapping("/admin/ad")
public class AdController {

    @Autowired
    private AdService adService;

    /*显示及查找*/
    @RequestMapping("/list")
    public BaseRespModel getList(int page, int limit, String sort, String order,String name,String content){
        List<Ad> adList = adService.selectList(name,content);
        adList = adList.subList(limit * (page - 1), Math.min(adList.size(), limit * page));
        BaseRespModel<HashMap> baseRespModel = new BaseRespModel<>();
        HashMap<String, Object> data = new HashMap<>();
        /*得到total值*/
        PageInfo<Ad> pageInfo = new PageInfo<>(adList);
        long total = pageInfo.getTotal();
        data.put("total",total);
        /*得到items*/
        data.put("items",adList);
        /*设置baseRespModel*/
        baseRespModel.setErrno(0);
        baseRespModel.setErrmsg("成功");
        baseRespModel.setData(data);
        return baseRespModel;
    }
    /*增*/
    @RequestMapping("/create")
    public BaseRespModel create(@RequestBody Ad ad){
        int i = adService.add(ad);
        BaseRespModel<Ad> baseRespModel = new BaseRespModel<>();
        baseRespModel.setData(ad);
        baseRespModel.setErrmsg("成功");
        baseRespModel.setErrno(0);
        return baseRespModel;
    }
    /*删*/
    @RequestMapping("/delete")
    public BaseRespModel delete(@RequestBody Ad ad){
        BaseRespModel<Object> respModel = new BaseRespModel<>();
        adService.delete(ad);
        respModel.setErrmsg("成功");
        respModel.setErrno(0);
        return respModel;
    }
    /*改*/
    @RequestMapping("/update")
    public BaseRespModel update(@RequestBody Ad ad){
        BaseRespModel<Ad> respModel = new BaseRespModel<>();
        int resultAd =adService.update(ad);
        if (resultAd>1){
            respModel.setData(ad);
            respModel.setErrno(0);
            respModel.setErrmsg("成功");
            return respModel;
        }else {
            return null;
        }
    }
}

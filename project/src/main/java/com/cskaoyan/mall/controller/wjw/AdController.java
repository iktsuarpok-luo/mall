package com.cskaoyan.mall.controller.wjw;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.service.wjw.AdService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/list")
    public BaseRespModel getList(int page, int limit, String sort, String order){
//        PageHelper.startPage(page,limit);
        List<Ad> adList = adService.selectList(sort,order);
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
    public BaseRespModel create(Ad ad){
        int i = adService.add(ad);
        BaseRespModel<HashMap> baseRespModel = new BaseRespModel<>();
        HashMap<String,Object> data = new HashMap<>();
        if (i>1){
            baseRespModel.setData(data);
            baseRespModel.setErrmsg("成功");
            baseRespModel.setErrno(0);
            return baseRespModel;
        }else {
            return null;
        }
    }
//    /*删*/
//    @RequestMapping("/delete")
//    public

}

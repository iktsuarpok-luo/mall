package com.cskaoyan.mall.controller.xw;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.service.xw.UserFootPrintService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("wx/footprint")
public class WxFootPrintController {
    @Autowired
    UserFootPrintService userFootPrintService;
    @Autowired
    GoodsMapper goodsMapper;
    @RequestMapping("list")
    public BaseRespModel footprintList(int page,int size){

        BaseRespModel resp = new BaseRespModel();
        try {
            Subject subject = SecurityUtils.getSubject();
            User user = (User)subject.getPrincipal();
            List<Footprint> list = userFootPrintService.queryFootPrint(page, size, user.getId(), null);
            List<Footprint> list1 = list.subList((page - 1) * size, Math.min(page * size, list.size()));
            int count = userFootPrintService.countFootprintById(user.getId());
            HashMap data = new HashMap();
            data.put("totalPages",(int)Math.ceil(count/size));
            ArrayList list2 = new ArrayList();
            for (Footprint footprint : list1) {
                GoodsExample goodsExample = new GoodsExample();
                goodsExample.createCriteria().andGoodsSnEqualTo(footprint.getGoodsId().toString());
                List<Goods> goods = goodsMapper.selectByExample(goodsExample);
                HashMap bean = new HashMap();
                bean.put("addTime",footprint.getAddTime());
                bean.put("brief",goods.get(0).getBrief());
                bean.put("goodsId",goods.get(0).getGoodsSn());
                bean.put("id",goods.get(0).getId());
                bean.put("name",goods.get(0).getName());
                bean.put("picUrl",goods.get(0).getPicUrl());
                bean.put("retailPrice",goods.get(0).getRetailPrice());
                list2.add(bean);
            }
            data.put("footprintList",list2);
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

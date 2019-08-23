package com.cskaoyan.mall.controller.lxs;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.lxs.lxsFeedback;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.cskaoyan.mall.mapper.CartMapper;
import com.cskaoyan.mall.mapper.GoodsproductMapper;
import com.cskaoyan.mall.service.lxs.WxFeedbackService;
import com.cskaoyan.mall.service.zt.GoodsService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("wx")
public class WxFeedbackController {
    @Autowired
    WxFeedbackService wxFeedbackService;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsproductMapper goodsproductMapper;
    @PostMapping("feedback/submit")
    public Map feeedback(@RequestBody lxsFeedback feedback){
        wxFeedbackService.insert(feedback);
        HashMap map = new HashMap();
        map.put("errmsg","成功");
        map.put("errno",0);
        return map;

    }
}

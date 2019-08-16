package com.cskaoyan.mall.controller.zxin;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.service.zxin.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:张鑫
 * @Time:2019年8月16日16:28:22
 * @Note:配置管理
 * @Version：0.0.1
 */
@RestController
@RequestMapping("admin/config")
public class ConfigController {
    @Autowired
    ConfigService configService;

    // 商场配置的显示功能
    @RequestMapping(value = "mall", method = RequestMethod.GET)
    public BaseRespModel mall() {
        BaseRespModel<Map> systemBaseRespModel = new BaseRespModel<>();
        systemBaseRespModel.setErrno(0);
        systemBaseRespModel.setErrmsg("成功");

        HashMap<String, Object> map = configService.selectMall();
        systemBaseRespModel.setData(map);
        return systemBaseRespModel;
    }

    // 商场配置的提交功能
    @RequestMapping(value = "mall", method = RequestMethod.POST)
    public BaseRespModel mallPost(@RequestBody Map<String, Object> map) {
        BaseRespModel<Map> systemBaseRespModel = new BaseRespModel<>();
        systemBaseRespModel.setErrno(0);
        systemBaseRespModel.setErrmsg("成功");

        try {
            configService.updateMall(map);
            return systemBaseRespModel;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 运费配置的显示功能
    @RequestMapping(value = "express", method = RequestMethod.GET)
    public BaseRespModel express() {
        BaseRespModel<Map> expressBaseRespModel = new BaseRespModel<>();
        expressBaseRespModel.setErrno(0);
        expressBaseRespModel.setErrmsg("成功");

        HashMap<String, Object> map = configService.selectExpress();
        expressBaseRespModel.setData(map);
        return expressBaseRespModel;
    }

    // 运费配置的提交功能
    @RequestMapping(value = "express", method = RequestMethod.POST)
    public BaseRespModel expressPost(@RequestBody Map<String, Object> map) {
        BaseRespModel<Map> systemBaseRespModel = new BaseRespModel<>();
        systemBaseRespModel.setErrno(0);
        systemBaseRespModel.setErrmsg("成功");

        try {
            configService.updateExpress(map);
            return systemBaseRespModel;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
    }
}

    // 订单配置的显示功能
    @RequestMapping(value = "order", method = RequestMethod.GET)
    public BaseRespModel order() {
        BaseRespModel<Map> expressBaseRespModel = new BaseRespModel<>();
        expressBaseRespModel.setErrno(0);
        expressBaseRespModel.setErrmsg("成功");

        HashMap<String, Object> map = configService.selectOrder();
        expressBaseRespModel.setData(map);
        return expressBaseRespModel;
    }

    // 订单配置的提交功能
    @RequestMapping(value = "order", method = RequestMethod.POST)
    public BaseRespModel orderPost(@RequestBody Map<String, Object> map) {
        BaseRespModel<Map> systemBaseRespModel = new BaseRespModel<>();
        systemBaseRespModel.setErrno(0);
        systemBaseRespModel.setErrmsg("成功");

        try {
            configService.updateOrder(map);
            return systemBaseRespModel;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // 小程序配置的显示功能
    @RequestMapping(value = "wx", method = RequestMethod.GET)
    public BaseRespModel wx() {
        BaseRespModel<Map> expressBaseRespModel = new BaseRespModel<>();
        expressBaseRespModel.setErrno(0);
        expressBaseRespModel.setErrmsg("成功");

        HashMap<String, Object> map = configService.selectWx();
        expressBaseRespModel.setData(map);
        return expressBaseRespModel;
    }

    // 小程序配置的提交功能
    @RequestMapping(value = "wx", method = RequestMethod.POST)
    public BaseRespModel wxPost(@RequestBody Map<String, Object> map) {
        BaseRespModel<Map> systemBaseRespModel = new BaseRespModel<>();
        systemBaseRespModel.setErrno(0);
        systemBaseRespModel.setErrmsg("成功");

        try {
            configService.updateWx(map);
            return systemBaseRespModel;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

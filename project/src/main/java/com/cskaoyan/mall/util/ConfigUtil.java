package com.cskaoyan.mall.util;

import com.cskaoyan.mall.bean.BaseRespModel;

import java.util.Map;

public class ConfigUtil {
    public void BaseUitl() {
        BaseRespModel<Map> systemBaseRespModel = new BaseRespModel<>();
        systemBaseRespModel.setErrno(0);
        systemBaseRespModel.setErrmsg("成功");
    }

}

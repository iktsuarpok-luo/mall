package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Grouponrules;

import java.util.List;

/**
 * @author ethan
 * @date 2019/8/19 16:19
 */
public interface GroupRuleService {
    public List<Grouponrules> getList(int goodsId);
    public List<Grouponrules> getList(int page, int limit);
    public List<Grouponrules> getList(int page,int limit,String sort, String order);
    public List<Grouponrules> getList(String sort, String order, Integer goodsId);

    int add(Grouponrules grouponrules);

    int delete(Grouponrules grouponrules);

    int update(Grouponrules grouponrules);

    Grouponrules selectRulesById(Integer rulesId);

    List<Grouponrules> getLimitList(int i, String id, String desc);
}

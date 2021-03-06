package com.cskaoyan.mall.service.wjw;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.Grouponrules;
import com.cskaoyan.mall.bean.GrouponrulesExample;
import com.cskaoyan.mall.mapper.GrouponrulesMapper;
import com.cskaoyan.mall.service.zt.GoodsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ethan
 * @date 2019/8/19 16:19
 */
@Service
public class GroupRuleServiceImpl implements GroupRuleService{
    @Autowired
    GrouponrulesMapper grouponrulesMapper;

    @Autowired
    GoodsService goodsService;

    @Autowired
    GroupRuleService groupRuleService;
    /**
     * 通过goodsId查询团购规则
     * @param goodsId
     * @return
     */
    @Override
    public List<Grouponrules> getList(int goodsId) {
        GrouponrulesExample grouponrulesExample = new GrouponrulesExample();
        grouponrulesExample.or().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return grouponrulesMapper.selectOneByExample(grouponrulesExample);
    }
    @Override
    public List<Grouponrules> getList(int page, int limit) {
        return getList(page, limit,"add_time","desc");
    }

    /**
     * 获取团购规则列表
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @Override
    public List<Grouponrules> getList(int page, int limit, String sort, String order) {
        GrouponrulesExample grouponrulesExample = new GrouponrulesExample();
        grouponrulesExample.or().andDeletedEqualTo(false);
        grouponrulesExample.setOrderByClause(sort+""+order);
        PageHelper.startPage(page,limit);
        return grouponrulesMapper.selectByExample(grouponrulesExample);
    }

    @Override
    public List<Grouponrules> getList( String sort, String order, Integer goodsId) {
        GrouponrulesExample grouponrulesExample = new GrouponrulesExample();
        GrouponrulesExample.Criteria criteria = grouponrulesExample.createCriteria();
        if (goodsId!=null){
            criteria.andGoodsIdEqualTo(goodsId);
        }
//        grouponrulesExample.setOrderByClause(sort+" "+order);
        criteria.andDeletedEqualTo(false);
        List<Grouponrules> grouponrulesList = grouponrulesMapper.selectByExample(grouponrulesExample);
        return grouponrulesList;
    }

    @Override
    public int add(Grouponrules grouponrules) {
        grouponrules.setAddTime(LocalDateTime.now());
        grouponrules.setUpdateTime(LocalDateTime.now());
        return grouponrulesMapper.insert(grouponrules);
    }

    @Override
    public int delete(Grouponrules grouponrules) {
        Integer id = grouponrules.getId();
        return grouponrulesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Grouponrules grouponrules) {
        grouponrules.setUpdateTime(LocalDateTime.now());
        return grouponrulesMapper.updateByPrimaryKey(grouponrules);
    }

    @Override
    public Grouponrules selectRulesById(Integer rulesId) {
        return grouponrulesMapper.selectByPrimaryKey(rulesId);
    }

    @Override
    public List<Map<String, Object>> getLimitList(int i, String order, String sort) {
        List<Grouponrules> grouponrulesList = groupRuleService.getList(sort,order,null);
        List<Map<String, Object>> grouponData = new ArrayList<>();
        for (Grouponrules grouponrules : grouponrulesList) {
            HashMap<String, Object> rowData = new HashMap<>();
            Integer grouponMember = grouponrules.getDiscountMember();
            Integer goodsId = grouponrules.getGoodsId();
            Goods goods = goodsService.findGoodsById(goodsId);
            rowData.put("goods",goods);
            int discount = grouponrules.getDiscount();
            int grouponPrice= goods.getCounterPrice()-discount;
            rowData.put("groupon_member",grouponMember);
            rowData.put("groupon_price",grouponPrice);
            grouponData.add(rowData);
        }
        return grouponData.subList(0, Math.min(grouponData.size(),i));
    }

    @Override
    public Grouponrules selectByGoodsId(Integer goodsId) {
        return grouponrulesMapper.selectByGoodsId(goodsId);
    }
}

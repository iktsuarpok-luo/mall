package com.cskaoyan.mall.service.wjw;
import com.cskaoyan.mall.bean.Grouponrules;
import com.cskaoyan.mall.bean.GrouponrulesExample;
import com.cskaoyan.mall.mapper.GrouponrulesMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ethan
 * @date 2019/8/19 16:19
 */
@Service
public class GroupRuleServiceImpl implements GroupRuleService{
    @Autowired
    GrouponrulesMapper grouponrulesMapper;

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
        grouponrulesExample.setOrderByClause(sort+" "+order);

        criteria.andDeletedEqualTo(false);
        return grouponrulesMapper.selectByExample(grouponrulesExample);
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
    public List<Grouponrules> getLimitList(int i, String id, String desc) {

       /* grouponrulesMapper.selectLimitList();
        grouponrulesList.subList(0,i);*/
        return null;
    }
}

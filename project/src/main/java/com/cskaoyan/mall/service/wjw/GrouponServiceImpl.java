package com.cskaoyan.mall.service.wjw;

import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponExample;
import com.cskaoyan.mall.mapper.GrouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ethan
 * @date 2019/8/19 9:31
 */
@Service
public class GrouponServiceImpl implements GrouponService{

    @Autowired
    GrouponMapper grouponMapper;


    @Override
    public List<Groupon> getListRecord(String sort, String order, Integer goodsId) {
        GrouponExample grouponExample = new GrouponExample();
        GrouponExample.Criteria criteria = grouponExample.createCriteria();
        if(goodsId!=null){
            criteria.andGrouponIdEqualTo(goodsId);
        }
        grouponExample.setOrderByClause(sort+" "+order);
        return grouponMapper.selectByExample(grouponExample);
    }

    @Override
    public List<Groupon> selectGrouponById(Integer id) {
        GrouponExample grouponExample = new GrouponExample();
        grouponExample.or().andUserIdEqualTo(id).andGrouponIdNotEqualTo(0).andDeletedEqualTo(false).andPayedEqualTo(true);
        grouponExample.setOrderByClause("add_time desc");
        return grouponMapper.selectByExample(grouponExample);
    }
}

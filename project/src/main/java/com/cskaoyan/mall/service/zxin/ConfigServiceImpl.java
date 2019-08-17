package com.cskaoyan.mall.service.zxin;

import com.cskaoyan.mall.bean.System;
import com.cskaoyan.mall.bean.SystemExample;
import com.cskaoyan.mall.mapper.SystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    SystemMapper systemMapper;

    @Override
    public HashMap<String, Object> selectMall() {
        List<System> list = systemMapper.selectMall();
        HashMap<String, Object> map = new HashMap<>();
        for (System system : list) {
            map.put(system.getKeyName(), system.getKeyValue());
        }
        return map;
    }


    @Override
    public HashMap<String, Object> selectExpress() {
        List<System> list = systemMapper.selectExpress();
        HashMap<String, Object> map = new HashMap<>();
        for (System system : list) {
            map.put(system.getKeyName(), system.getKeyValue());
        }
        return map;
    }

    @Override
    public HashMap<String, Object> selectOrder() {
        List<System> list = systemMapper.selectOrder();
        HashMap<String, Object> map = new HashMap<>();
        for (System system : list) {
            map.put(system.getKeyName(), system.getKeyValue());
        }
        return map;
    }

    @Override
    public HashMap<String, Object> selectWx() {
        List<System> list = systemMapper.selectWx();
        HashMap<String, Object> map = new HashMap<>();
        for (System system : list) {
            map.put(system.getKeyName(), system.getKeyValue());
        }
        return map;
    }

    @Override
    public void updateMall(Map<String, Object> map) {
        List<System> list = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System system = new System();
            system.setKeyName(entry.getKey());
            system.setKeyValue((String) entry.getValue());
            list.add(system);
        }
        for (System system : list) {
            SystemExample systemExample = new SystemExample();
            systemExample.createCriteria().andKeyNameEqualTo(system.getKeyName());
            systemMapper.updateByExampleSelective(system, systemExample);
        }
    }

    @Override
    public void updateExpress(Map<String, Object> map) {
        List<System> list = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System system = new System();
            system.setKeyName(entry.getKey());
            system.setKeyValue((String) entry.getValue());
            list.add(system);
        }
        for (System system : list) {
            SystemExample systemExample = new SystemExample();
            systemExample.createCriteria().andKeyNameEqualTo(system.getKeyName());
            systemMapper.updateByExampleSelective(system,systemExample);
        }
    }

    @Override
    public void updateOrder(Map<String, Object> map) {
        List<System> list = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System system = new System();
            system.setKeyName(entry.getKey());
            system.setKeyValue((String) entry.getValue());
            list.add(system);
        }
        for (System system : list) {
            SystemExample systemExample = new SystemExample();
            systemExample.createCriteria().andKeyNameEqualTo(system.getKeyName());
            systemMapper.updateByExampleSelective(system,systemExample);
        }
    }

    @Override
    public void updateWx(Map<String, Object> map) {
        List<System> list = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System system = new System();
            system.setKeyName(entry.getKey());
            system.setKeyValue(String.valueOf(entry.getValue()));
            list.add(system);
        }
        for (System system : list) {
            SystemExample systemExample = new SystemExample();
            systemExample.createCriteria().andKeyNameEqualTo(system.getKeyName());
            systemMapper.updateByExampleSelective(system,systemExample);
        }
    }
}

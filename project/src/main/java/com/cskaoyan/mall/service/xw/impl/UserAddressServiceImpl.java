package com.cskaoyan.mall.service.xw.impl;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.AddressExample;
import com.cskaoyan.mall.bean.UserExample;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.cskaoyan.mall.service.xw.UserAddressService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    AddressMapper addressMapper;
    @Override
    public List<Address> queryAddress(int page, int limit, String userId, String name) {
        AddressExample addressExample = new AddressExample();
        PageHelper.startPage(page,limit);
        if(userId == null || userId.equals("")){
            userId = null;
        }
        if(name == null || name.equals("")){
            name = null;
        }
        if(userId == null && name == null){
            List<Address> addressList = addressMapper.selectByExample(addressExample);
            return addressList;
        }

        if(userId != null && name == null){
            addressExample.createCriteria().andUserIdEqualTo(Integer.parseInt(userId));
            List<Address> addressList = addressMapper.selectByExample(addressExample);
            return addressList;
        }

        if(userId == null && name != null){
            String s = "%" + name + "%";
            addressExample.createCriteria().andNameLike(s);
            List<Address> addressList = addressMapper.selectByExample(addressExample);
            return addressList;
        }

        if(userId != null && name != null){
            String s = "%" + name + "%";
            addressExample.createCriteria().andNameLike(s).andUserIdEqualTo(Integer.parseInt(userId));
            List<Address> addressList = addressMapper.selectByExample(addressExample);
            return addressList;
        }
        return null;
    }

    }


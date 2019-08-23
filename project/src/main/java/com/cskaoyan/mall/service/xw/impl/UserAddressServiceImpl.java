package com.cskaoyan.mall.service.xw.impl;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.AddressExample;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.UserExample;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.cskaoyan.mall.service.xw.UserAddressService;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
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

    @Override
    public List<Address> getAddressListByUser(Integer id) {
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andUserIdEqualTo(id);
        return addressMapper.selectByExample(addressExample);
    }

    @Override
    public Address getAddressById(int id) {
        return addressMapper.selectByPrimaryKey(id);
    }

    @Override
    public Address update(Address address,int id) {
        if(address.getId()==0){
            address.setUserId(id);
            Date date = new Date();
            address.setUpdateTime(date);
            address.setAddTime(date);
            address.setDeleted(false);
            address.setId(null);
            addressMapper.insert(address);
        }else {
            Address oldAddress = addressMapper.selectByPrimaryKey(address.getId());
            address.setUpdateTime(new Date());
            address.setDeleted(false);
            address.setAddTime(oldAddress.getAddTime());
            address.setUserId(oldAddress.getUserId());
            addressMapper.updateByPrimaryKey(address);
        }
        if (address.getIsDefault()){
            AddressExample addressExample = new AddressExample();
            addressExample.createCriteria().andIdNotEqualTo(address.getId());
            List<Address> list = addressMapper.selectByExample(addressExample);
            for (Address address1 : list) {
                address1.setIsDefault(false);
                addressMapper.updateByPrimaryKey(address1);
            }
        }
        return address;
    }

    @Override
    public void delete(int id) {
        addressMapper.deleteByPrimaryKey(id);
    }

}


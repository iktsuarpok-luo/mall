package com.cskaoyan.mall.service.lxs.impl;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.BrandExample;
import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.bean.StorageExample;
import com.cskaoyan.mall.mapper.StorageMapper;
import com.cskaoyan.mall.service.lxs.UserStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStorageSeviceImpl implements UserStorageService {
    @Autowired
    StorageMapper storageMapper;

    @Override
    public List<Storage> getStorageList(int page, int limit, String sort, String order, String name, String key) {
        StorageExample storageExample = new StorageExample();
        storageExample.setOrderByClause(sort+" "+order);
        if(name!=null&&name!=""){
            storageExample.createCriteria().andNameLike("%"+name+"%");
        }
        if(key!=null&&key!=""){
            storageExample.createCriteria().andKeyEqualTo(key);
        }
        List<Storage> list = storageMapper.selectByExample(storageExample);
        return list.subList(limit*(page-1),Math.min(limit*page,list.size()));
    }

    @Override
    public long countStorage() {
        return storageMapper.countByExample(new StorageExample());
    }

    @Override
    public Storage update(Storage storage) {
        //Storage storage1 = storageMapper.selectByPrimaryKey(storage.getId());
        /*if (!storage1.getPicUrl().equals(brand.getPicUrl())) {
            storageService.deleteByUrl(brand1.getPicUrl());

            StorageExample storageExample = new StorageExample();
            storageExample.createCriteria().andUrlEqualTo(brand1.getPicUrl());
            storageMapper.deleteByExample(storageExample);
        }*/
        storageMapper.updateByPrimaryKey(storage);
        return storage;
    }

    @Override
    public void delete(Storage storage) {
        storageMapper.deleteByPrimaryKey(storage.getId());
    }
}

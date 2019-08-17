package com.cskaoyan.mall.service.lxt.impl;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.bean.StorageExample;
import com.cskaoyan.mall.mapper.StorageMapper;
import com.cskaoyan.mall.service.lxt.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class StorageServiceImpl implements StorageService {
    @Value("${filePath}")
    String filePath;
    @Autowired
    StorageMapper storageMapper;
    @Override
    public Storage add(Storage storage) {
        storageMapper.insert(storage);
        return storage;
    }

    @Override
    public void deleteByUrl(String picUrl) {
        StorageExample storageExample = new StorageExample();
        storageExample.createCriteria().andUrlEqualTo(picUrl);
        Storage storage = storageMapper.selectByExample(storageExample).get(0);
        File file = new File(filePath+storage.getKey());
        file.delete();
    }
}

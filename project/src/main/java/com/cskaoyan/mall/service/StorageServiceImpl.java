/*
package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.bean.wjw.StorageW;
import com.cskaoyan.mall.mapper.StorageMapper;
import com.cskaoyan.mall.service.lxt.StorageService;
import com.cskaoyan.mall.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

*/
/**
 * @author ethan
 * @date 2019/8/16 20:39
 *//*

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    StorageMapper storageMapper;
    @Override
    public StorageW upFile(String name, String type, long size, InputStream inputStream) {
        StorageW storageW = new StorageW();
        String key = genKey(name);
        String url = genUrl(key);

        storageW.setName(name);
        storageW.setKey(key);
        storageW.setType(type);
        storageW.setSize((int) size);
        storageW.setUrl(url);
        storageW.setAddTime(LocalDateTime.now());
        storageW.setUpdateTime(LocalDateTime.now());
        return storageW;
    }

    private String genUrl(String key) {
        return key;
    }

    private String genKey(String name) {
        */
/*随机取文件前20位字母进行拼接*//*

        int i = name.lastIndexOf(".");
        String substring = name.substring(i);
        String key = RandomUtils.getRandomString(20)+substring;
        return key;
    }
}
*/

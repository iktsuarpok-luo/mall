package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.bean.wjw.StorageW;
import com.cskaoyan.mall.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ethan
 * @date 2019/8/16 20:28
 */
@RestController
@RequestMapping("admin/storage")
public class StorageController {
    @Autowired
    StorageService storageService;
    /*文件上传功能*/
    @RequestMapping("/create")
    public BaseRespModel create(@RequestParam("file") MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String type = file.getContentType();
        long size = file.getSize();
        InputStream inputStream = file.getInputStream();
        StorageW storageW =storageService.upFile(name,type,size,inputStream);
        BaseRespModel<StorageW> respModel = new BaseRespModel<>();
        respModel.setData(storageW);
        respModel.setErrno(0);
        respModel.setErrmsg("成功");
        return respModel;
    }
}

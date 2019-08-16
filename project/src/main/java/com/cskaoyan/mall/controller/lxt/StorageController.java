package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.service.lxt.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@RequestMapping("admin/storage")
@RestController
public class StorageController {
    @Value("${filePath}")
    String filePath;
    @Autowired
    StorageService storageService;
    @RequestMapping("create")
    public BaseRespModel addPic(MultipartFile file) throws IOException {
        BaseRespModel resp = new BaseRespModel();
        try {
            String fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            if (!suffixName.endsWith("jpg") && !suffixName.endsWith("png") && suffixName.endsWith("gif")) {
                Exception e = new Exception("文件格式不正确");
                throw e;
            }
            fileName = UUID.randomUUID() + suffixName;
            File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            String url = "/static/"+fileName;

            Storage storage = new Storage();
            storage.setUrl(url);
            storage.setSize((int) file.getSize());
            storage.setKey(fileName);
            storage.setType(suffixName);
            storage.setDeleted(false);
            storage.setName(fileName);
            Date date = new Date();
            storage.setAddTime(date);
            storage.setUpdateTime(date);
            storage=storageService.add(storage);
            resp.setData(storage);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }catch (Exception e){
            resp.setErrno(1);
            resp.setErrmsg(e.getMessage());
        }
        return resp;
    }
}

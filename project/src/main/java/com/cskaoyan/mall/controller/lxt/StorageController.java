package com.cskaoyan.mall.controller.lxt;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.service.lxt.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        //oss
        InputStream inputStream = file.getInputStream();
        String contentType = file.getContentType();
        long size = file.getSize();  int size1= (int) size;

        //oss需要的4个参数
        String endPoint = "oss-cn-beijing.aliyuncs.com";
        String bucket ="lixishuang";
        String secret = "vzdaAF65VCcL2XHY0ZcBmhL6SxVRAW";
        String secreKey = "LTAIHtuGX0HOs9EE";

        //oss关联代码
        String fileNameoss=UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(fileNameoss);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);
        objectMetadata.setContentType(contentType);

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileNameoss, inputStream, objectMetadata);
        OSSClient ossClient = new OSSClient(endPoint, secreKey, secret);
        ossClient.putObject(putObjectRequest);




        //lxt 源代码
        BaseRespModel resp = new BaseRespModel();
        try {
            String fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            if (!suffixName.endsWith("jpg") && !suffixName.endsWith("png") && suffixName.endsWith("gif")) {
                Exception e = new Exception("文件格式不正确");
                throw e;
            }
            SimpleDateFormat df=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
            fileName = df.format(new Date())+UUID.randomUUID() + suffixName;
           /*这里是储存到本地的代码
           File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            String url = "/static/"+fileName;*/
            String urloss ="https://lixishuang.oss-cn-beijing.aliyuncs.com/"+fileNameoss;
            Storage storage = new Storage();
            storage.setUrl(urloss);
            storage.setSize(size1);
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
            resp.setErrmsg("失败");
        }
        return resp;
    }
}

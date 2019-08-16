package com.cskaoyan.mall.controller.lxt;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RequestMapping("admin/storage")
@RestController
public class StorageController {
    @Value("${filePath}")
    String filePath;
    @RequestMapping("create")
    public BaseRespModel addPic(MultipartFile file) throws IOException {
        try {
            String path = filePath+file.getOriginalFilename();
            File Pathfile = new File(path);
            file.transferTo(Pathfile);
            HashMap<String, Object> map = new HashMap<>();
            int i = path.indexOf("\\static");
            String replace = path.substring(i).replace("\\", "/");
        }catch (Exception e){

        }
        return null;
    }
}

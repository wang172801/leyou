package com.hp.services;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadServices {
    @Autowired
    private FastFileStorageClient storageClient;

    public String uploadImage(MultipartFile file) {
      /*  //创建file对象
        File f=new File("D://upload");
        if (!f.exists()){
            //创建文件夹
        }
        try {
            file.transferTo(new File(f,file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "http://image.leyou.com/"+file.getOriginalFilename();*/
        //获取浏览器传过来的后缀
        String url=null;
        String or=file.getOriginalFilename();
        String pre=StringUtils.substringAfter(or,".");
        //图片的上传
        try {
           StorePath storePath= storageClient.uploadFile(file.getInputStream(),file.getSize(),pre,null);
            url= "http://image.leyou.com/"+storePath.getFullPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return url;
    }
}

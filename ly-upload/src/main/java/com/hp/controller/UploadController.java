package com.hp.controller;

import com.hp.services.UploadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("upload")//@RequestParam("upload")//
public class UploadController {
    //注入services层
    @Autowired
    private UploadServices uploadServices;

    @PostMapping("image")
    private ResponseEntity<String> uploadImage(@RequestParam("file")MultipartFile file){
        String url=uploadServices.uploadImage(file);
        if (null!=url){
            return ResponseEntity.ok(url);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

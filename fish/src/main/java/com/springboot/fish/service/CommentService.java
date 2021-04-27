package com.springboot.fish.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class CommentService {
    //保存文件
    public static boolean saveFile(MultipartFile partfile){
        try{
            //文件名
            String filename=partfile.getOriginalFilename();
            //文件大小
            Long size=partfile.getSize();
            //文件类型
            String contentType=partfile.getContentType();
            //存储路径
            String filepath="G://test//"+filename;

            File demofile=new File(filepath);
            //上传文件
            partfile.transferTo(demofile);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //传送文件

}

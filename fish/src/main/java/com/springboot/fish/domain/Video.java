package com.springboot.fish.domain;

import lombok.Data;

//定义文章实体
@Data
public class Video {
    private int vid;
    private int uid;       //作者
    private int tid;      //视频类型
    private String title;  //视频标题
    private String text;   //视频简介
    private String video;  //视频的存储路径
    private String picture;//视频封面路径
}

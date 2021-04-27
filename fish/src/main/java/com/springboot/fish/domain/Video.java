package com.springboot.fish.domain;

import lombok.Data;

//定义文章实体
@Data
public class Video {
    private Long vid;
    private String title;  //视频标题
    private Long uid;      //作者
    private String video;  //视频的存储路径
    private String picture;//视频封面路径
}

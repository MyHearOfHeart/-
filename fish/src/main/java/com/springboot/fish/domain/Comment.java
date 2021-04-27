package com.springboot.fish.domain;

import lombok.Data;

import java.util.Date;

//评论信息
@Data
public class Comment {
    private Long cid;
    private Long vid;
    private Long uid;
    private String text;
    private Date time;
}

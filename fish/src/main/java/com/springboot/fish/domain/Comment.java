package com.springboot.fish.domain;

import lombok.Data;

import java.util.Date;

//评论信息
@Data
public class Comment {
    private int cid;
    private int vid;
    private int uid;
    private String text;
    private String time;
}

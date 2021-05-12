package com.springboot.fish.service;

import com.springboot.fish.domain.User;
import com.springboot.fish.domain.Video;

import java.sql.*;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        AllService service = new AllService();
        Video video = new Video();
        video.setUid(2000);
        video.setVideo("0");
        video.setPicture("0");
        video.setText("0");
        video.setTitle("0");
        service.insertVideo(video);
        List<Video> videos = service.queryAllVideos();
        System.out.println(videos.get(videos.size()-1).getVid());

    }

}

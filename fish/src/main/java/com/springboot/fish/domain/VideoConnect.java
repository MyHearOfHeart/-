package com.springboot.fish.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoConnect {
    static Connection connection;
    static PreparedStatement statement=null;

    public VideoConnect(Connection conn) {
        connection=conn;
    }

    //查询所有视频信息
    public static List<Video> queryAllVideos(){
        List<Video> videos= new ArrayList<>();
        String sql="select * from video";
        ResultSet res=null;
        try {
            statement=connection.prepareStatement(sql);
            System.out.println("初始化句柄");
            res=statement.executeQuery();
            System.out.println("执行查询");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("查询视频失败");
        }
        try {
            while(res.next()){
                Video video = new Video();
                video.setVid(res.getInt("vid"));
                video.setUid(res.getInt("uid"));
                video.setTid(res.getInt("tid"));
                video.setTitle(res.getString("title"));
                video.setText(res.getString("text"));
                video.setVideo(res.getString("video"));
                video.setPicture(res.getString("picture"));
                videos.add(video);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return videos;
    }

    //用id查询某个视频
    public static Video queryByVid(int vid){
        String sql="select * from video where vid=?";
        Video video=new Video();
        ResultSet res=null;
        try {
            statement=connection.prepareStatement(sql);
            System.out.println("初始化句柄");
            statement.setLong(1,vid);
            res=statement.executeQuery();
            System.out.println("执行查询");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("查询视频失败");
        }
        try {
            while(res.next()){
                video.setUid(res.getInt("uid"));
                video.setTid(res.getInt("tid"));
                video.setTitle(res.getString("title"));
                video.setText(res.getString("text"));
                video.setVideo(res.getString("video"));
                video.setPicture(res.getString("picture"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return video;
    }

    //存储一个视频
    public static int insertVideo(Video video){
        String sql="insert into video(uid,tid,title,text,video,picture) values(?,?,?,?,?,?)";
        int succeed = 0;
        try {
            statement = connection.prepareStatement(sql);
            System.out.println("初始化句柄");
            statement.setInt(1,video.getUid());
            statement.setInt(2,video.getTid());
            statement.setString(3,video.getTitle());
            statement.setString(4,video.getText());
            statement.setString(5,video.getVideo());
            statement.setString(6,video.getPicture());
            succeed = statement.executeUpdate();
            System.out.println("插入数据");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("插入失败");
        }
        return succeed;
    }

    //删除视频
    public static int deleteVideo(Video video){
        String sql="delete from video where vid=?";
        int succeed = 0;
        try {
            statement = connection.prepareStatement(sql);
            System.out.println("初始化句柄");
            statement.setLong(1,video.getVid());
            succeed = statement.executeUpdate();
            System.out.println("删除数据");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("删除失败");
        }
        return succeed;
    }

    //根据标题查询视频
    public static List<Video> queryByTitle(String title){
        List<Video> videos= new ArrayList<>();
        String sql="select * from video where title=?";
        ResultSet res=null;
        try {
            statement=connection.prepareStatement(sql);
            System.out.println("初始化句柄");
            statement.setString(1,title);
            res=statement.executeQuery();
            System.out.println("执行查询");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("查询视频失败");
        }
        try {
            while(res.next()){
                Video video = new Video();
                video.setVid(res.getInt("vid"));
                video.setUid(res.getInt("uid"));
                video.setTid(res.getInt("tid"));
                video.setTitle(res.getString("title"));
                video.setText(res.getString("text"));
                video.setVideo(res.getString("video"));
                video.setPicture(res.getString("picture"));
                videos.add(video);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return videos;
    }

    //根据作者查询
    public static List<Video> queryByUid(int uid){
        List<Video> videos= new ArrayList<>();
        String sql="select * from video where uid=?";
        ResultSet res=null;
        try {
            statement=connection.prepareStatement(sql);
            System.out.println("初始化句柄");
            statement.setLong(1,uid);
            res=statement.executeQuery();
            System.out.println("执行查询");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("查询视频失败");
        }
        try {
            while(res.next()){
                Video video = new Video();
                video.setVid(res.getInt("vid"));
                video.setUid(res.getInt("uid"));
                video.setTid(res.getInt("tid"));
                video.setTitle(res.getString("title"));
                video.setText(res.getString("text"));
                video.setVideo(res.getString("video"));
                video.setPicture(res.getString("picture"));
                videos.add(video);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return videos;
    }

}

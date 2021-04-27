package com.springboot.fish.service;

import com.springboot.fish.domain.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.FileHandler;

public class AllService {
    static Connection connection = null;
    static UserConnect uconn = null;
    static VideoConnect vconn = null;
    static CommentConnect cconn = null;

    //连接数据库
    public AllService(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=test","sa","qrj200301");
            System.out.println("数据库连接成功");
            uconn = new UserConnect(connection);
            vconn = new VideoConnect(connection);
            cconn = new CommentConnect(connection);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("数据库连接失败");
        }
    }

    //断开连接
    public static void DisConnect() throws SQLException {
        connection.close();
        uconn = null;
        vconn = null;
        cconn = null;
        System.out.println("断开数据库连接");
    }

    //登录，成功的话返回用户（也算是查询用户吧）
    public static User Login(User us) {
        User user = uconn.queryByMail(us.getMail());
        //按理只有一个账号，返回用户对象
        if(user!=null && user.getPassword()==us.getPassword()){
            return user;
        }
        //否则返回空
        return null;
    }

    //用户名查询用户
    public static List<User> queryUserByName(String name){
        return uconn.queryByName(name);
    }

    //id名查询用户
    public static User queryUserByUid(Long uid)
    {
        return uconn.queryByUid(uid);
    }

    //找回密码
    public static String findPass(String mail){
        User user = uconn.queryByMail(mail);
        //按理只有一个账号，返回用户对象
        if(user!=null){
            /*
            发送邮件给用户邮箱
             */
            return user.getPassword();
        }
        //否则返回空
        return null;
    }

    //注册账号
    public static User Register(User user){
        //查询是否已注册
        User res=uconn.queryByMail(user.getMail());
        //账号已经存在
        if(res!=null){
            return null;
        }
        int succeed=uconn.insertUser(user);
        res=uconn.queryByMail(user.getMail());
        //注册成功返回对象
        return succeed==1?res:null;
    }

    //删除账号
    public static boolean deleteUser(User user){
        int succeed=uconn.deleteUser(user);
        return succeed==1?true:false;
    }

    //更新账号信息
    public static boolean updateUser(User user){
        int succeed=uconn.updateUser(user);
        return succeed==1?true:false;
    }

    //根据id查询某个视频信息
    public static Video queryVideoByVid(Long vid){
        return vconn.queryByVid(vid);
    }

    //存储一个视频
    public boolean insertVideo(Video video){
        int succeed=vconn.insertVideo(video);
        return succeed==1?true:false;
    }

    //删除一个视频
    public boolean deleteVideo(Video video){
        int succeed=vconn.deleteVideo(video);
        return succeed==1?true:false;
    }

    //查询某个作者的视频
    public static List<Video> queryVideoByAuthor(Long uid){
        return vconn.queryByUid(uid);
    }


    //根据标题查询视频
    public static List<Video> queryVideoByTitle(String title){
        return vconn.queryByTitle(title);
    }

    //查询某个视频的评论
    public static List<Comment> queryComment(Long vid){
        return cconn.queryByVid(vid);
    }

    //添加评论
    public static boolean insertComment(Comment comment){
        int succeed=cconn.insertComment(comment);
        return succeed==1?true:false;
    }



}

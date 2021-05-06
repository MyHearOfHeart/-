package com.springboot.fish.service;

import com.springboot.fish.domain.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //System.out.println(us);
        //System.out.println(user);
        if(user!=null && user.getPassword().equals(us.getPassword())){
            //System.out.println(user);
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
    public static User queryUserByUid(int uid)
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
    public static Video queryVideoByVid(int vid){
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
    public static List<Video> queryVideoByAuthor(int uid){
        return vconn.queryByUid(uid);
    }

    //查询所有视频
    public static List<Video> queryAllVideos(){
        return vconn.queryAllVideos();
    }

    //根据标题查询视频
    public static List<Video> queryVideoByTitle(String title){
        return vconn.queryByTitle(title);
    }

    //查询某个视频的评论
    public static List<Comment> queryComment(int vid){
        return cconn.queryByVid(vid);
    }

    //添加评论
    public static boolean insertComment(Comment comment){
        int succeed=cconn.insertComment(comment);
        return succeed==1?true:false;
    }

    //获取视频的评论
    public static Map video(int vid){
        Video video = queryVideoByVid(vid);
        List<Comment> comments = queryComment(vid);
        for(int i=0;i<comments.size();i++){
            System.out.println(comments.get(i).getUid());
        }
        User user = queryUserByUid(video.getUid());
        user.setPassword("");
        Map map = new HashMap();
        map.put("author",user);
        map.put("video",video);
        map.put("comment",comments);
        return map;
    }

    //查询功能
    public static Map searchAll(String keywords){
        List<User> users = queryUserByName(keywords);
        Map map = new HashMap();
        map.put("author",users);
        //搜索视频(标题)
        List<Video> videos = queryVideoByTitle(keywords);
        map.put("video",videos);
        return map;
    }

    //查询一条具体的评论
    public static Comment queryByCid(int cid){
        return cconn.queryByCid(cid);
    }

    //删除一条评论
    public static void deleteComment(int cid){
        cconn.deleteComment(cid);
    }


}

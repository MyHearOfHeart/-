package com.springboot.fish.domain;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentConnect {
    static Connection connection;
    static PreparedStatement statement=null;

    public CommentConnect(Connection conn) {
        /*
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=comment","sa","qrj200301");
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("数据库连接失败");
        }
         */
        connection=conn;
    }

    //查询某个视频的评论
    public static List<Comment> queryByVid(int vid){
        List<Comment> comments= new ArrayList<>();
        String sql="select * from comment where vid=?";
        ResultSet res=null;
        try {
            statement=connection.prepareStatement(sql);
            System.out.println("初始化句柄");
            statement.setInt(1,vid);
            res=statement.executeQuery();
            System.out.println("执行查询");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("查询评论失败");
        }
        try {
            while(res.next()){
                Comment comment = new Comment();
                comment.setCid(res.getInt("cid"));
                comment.setVid(res.getInt("vid"));
                comment.setUid(res.getInt("uid"));
                comment.setText(res.getString("text"));
                comment.setTime(res.getString("time"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return comments;
    }

    //根据id号查询评论
    public static Comment queryByCid(int cid){
        String sql="select * from comment where cid=?";
        Comment comment = new Comment();
        ResultSet res=null;
        try {
            statement=connection.prepareStatement(sql);
            System.out.println("初始化句柄");
            statement.setInt(1,cid);
            res=statement.executeQuery();
            System.out.println("执行查询");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("查询评论失败");
        }
        try {
            while(res.next()){
                comment.setCid(res.getInt("cid"));
                comment.setVid(res.getInt("vid"));
                comment.setUid(res.getInt("uid"));
                comment.setText(res.getString("text"));
                comment.setTime(res.getString("time"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return comment;
    }

    //存储一条评论
    public static int insertComment(Comment comment){
        String sql="insert into comment(vid,uid,text,time) values(?,?,?,?)";
        int succeed = 0;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1,comment.getVid());
            statement.setLong(2,comment.getUid());
            statement.setString(3,comment.getText());
            statement.setString(4,comment.getTime());
            succeed = statement.executeUpdate();
            System.out.println("插入评论");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("插入失败");
        }
        return succeed;
    }

    //删除一条评论
    public static void deleteComment(int cid){
        String sql="delete from comment where cid=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1,cid);
            System.out.println("删除评论");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("删除评论失败");
        }
    }

    //


}

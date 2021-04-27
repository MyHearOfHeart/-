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
    public static List<Comment> queryByVid(Long vid){
        List<Comment> comments= new ArrayList<>();
        Comment comment = new Comment();
        String sql="select * from comment where vid=?";
        ResultSet res=null;
        try {
            statement=connection.prepareStatement(sql);
            System.out.println("初始化句柄");
            statement.setLong(1,vid);
            res=statement.executeQuery();
            System.out.println("执行查询");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("查询评论失败");
        }
        try {
            while(res.next()){
                comment.setCid(res.getLong("cid"));
                comment.setVid(res.getLong("vid"));
                comment.setUid(res.getLong("uid"));
                comment.setText(res.getString("text"));
                comment.setTime(res.getDate("time"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return comments;
    }

    //存储一条评论
    public static int insertComment(Comment comment){
        String sql="insert into comment(vid,uid,text) values(?,?,?)";
        int succeed = 0;
        try {
            statement = connection.prepareStatement(sql);
            System.out.println("初始化句柄");
            statement.setLong(1,comment.getVid());
            statement.setLong(2,comment.getUid());
            statement.setString(3,comment.getText());
            succeed = statement.executeUpdate();
            System.out.println("插入数据");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("插入失败");
        }
        return succeed;
    }


}

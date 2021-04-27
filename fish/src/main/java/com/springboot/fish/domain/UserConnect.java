package com.springboot.fish.domain;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//连接用户数据库
public class UserConnect {
    static Connection connection=null;
    static PreparedStatement statement=null;

    public UserConnect(Connection conn) {
        /*
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=user","sa","qrj200301");
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("数据库连接失败");
        }
         */
        connection=conn;
    }

    //用名字查询账号
    public static  List<User> queryByName(String name){
        String sql="select * from user where cname=?";
        ResultSet res=null;
        List<User> users= new ArrayList<>();
        try {
            statement=connection.prepareStatement(sql);
            System.out.println("初始化句柄");
            statement.setObject(1,name);
            res=statement.executeQuery();
            System.out.println("执行查询");
            User user = new User();
            try {
                while(res.next()){
                    user.setUid(res.getLong("uid"));
                    user.setName(res.getString("name"));
                    user.setMail(res.getString("mail"));
                    user.setPassword(res.getString("password"));
                    users.add(user);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("名字查询账号失败");
        }
        return users;
    }

    //用邮箱查询账号，成功
    public static User queryByMail(String mail){
        String sql="select * from custom where mail=?";
        try {
            statement=connection.prepareStatement(sql);
            System.out.println("初始化句柄");
            statement.setString(1,mail);
            ResultSet res=statement.executeQuery();
            System.out.println("执行查询");
            List<User> users= new ArrayList<>();
            User user = new User();
            try {
                while(res.next()){
                    user.setUid(res.getLong("uid"));
                    user.setName(res.getString("cname"));
                    user.setMail(res.getString("mail"));
                    user.setPassword(res.getString("password"));
                    users.add(user);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(users.size()==1){
                return user;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("邮箱查询账号失败");
            return null;
        }
        return null;
    }

    //用id查询账号
    public static User queryByUid(Long uid){
        String sql="select * from custom where uid=?";
        try {
            statement=connection.prepareStatement(sql);
            System.out.println("初始化句柄");
            statement.setLong(1,uid);
            ResultSet res=statement.executeQuery();
            System.out.println("执行查询");
            List<User> users= new ArrayList<>();
            User user = new User();
            try {
                while(res.next()){
                    user.setUid(res.getLong("uid"));
                    user.setName(res.getString("cname"));
                    user.setMail(res.getString("mail"));
                    user.setPassword(res.getString("password"));
                    users.add(user);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return user;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("邮箱查询账号失败");
            return null;
        }
    }

    //创建账号，成功
    public static int insertUser(User user){
        String sql="insert into custom(cname,mail,password) values(?,?,?)";
        int succeed = 0;
        try {
            statement = connection.prepareStatement(sql);
            System.out.println("初始化句柄");
            statement.setString(1,user.getName());
            statement.setString(2,user.getMail());
            statement.setString(3,user.getPassword());
            succeed = statement.executeUpdate();
            System.out.println("插入数据");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("插入失败");
            return 0;
        }
        return succeed;
    }

    //删除账号
    public static int deleteUser(User user){
        String sql="delete from custom where uid=?";
        int succeed = 0;
        try {
            statement = connection.prepareStatement(sql);
            System.out.println("初始化句柄");
            statement.setLong(1,user.getUid());
            succeed = statement.executeUpdate();
            System.out.println("删除数据");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("删除失败");
        }
        return succeed;
    }

    //更新账号信息
    public static int updateUser(User user){
        String sql="update custom set cname=?,mail=?,password=? where uid=?";
        int succeed = 0;
        try {
            statement = connection.prepareStatement(sql);
            System.out.println("初始化句柄");
            statement.setString(1,user.getName());
            statement.setString(2,user.getMail());
            statement.setString(3,user.getPassword());
            statement.setLong(4,user.getUid());
            succeed = statement.executeUpdate();
            System.out.println("修改数据");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("修改失败");
        }
        return succeed;
    }


}

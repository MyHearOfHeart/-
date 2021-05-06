package com.springboot.fish.service;

import com.springboot.fish.domain.User;

import java.sql.*;

public class Test {

    public static void main(String[] args) {
        //直接输出user对象：User(uid=null, name=null, mail=123456789@qq.com, password=123456)
        AllService service = new AllService();
        User user=new User();
        user.setPassword("123456");
        user.setMail("18923456@qq.com");
        User res = service.Login(user);
        //System.out.println(res);

    }

    public static void userTest(AllService service){
        User user=new User();
        user.setMail("18923456@qq.com");
        user.setPassword("123456");
        user=service.Register(user);
        System.out.println("注册"+user);
        User res=service.Login(user);
        System.out.println("登录密码"+res.getPassword());

        res.setPassword("234567");
        System.out.println("更改"+service.updateUser(res));

        user=service.Login(user);
        System.out.println("更改后的密码： "+user.getPassword());

        System.out.println("删除账号"+service.deleteUser(user));

        res=service.Login(user);
        System.out.println("删除后的查询： "+res);
    }

    public static void videoTest(){}

    public static void commentTest(){

    }
}

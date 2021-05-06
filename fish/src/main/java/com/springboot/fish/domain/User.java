package com.springboot.fish.domain;

import lombok.Data;

//定义User实体
//使用@Data注解可以实现在编译器自动添加set和get函数的效果。
// 该注解是lombok提供的，需要在pom中引入依赖，引入不成功先下载插件
@Data
public class User {
    private int uid;
    private String name;
    private String mail;
    private String password;
}

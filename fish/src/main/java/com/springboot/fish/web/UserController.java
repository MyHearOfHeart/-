package com.springboot.fish.web;

import com.springboot.fish.domain.User;
import com.springboot.fish.service.AllService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    //在线的用户
    static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<Integer, User>());
    AllService service=new AllService();

    //进入登录界面
    @GetMapping("/login")
    public String Login(){
        return "login";
    }

    //进入注册界面
    @GetMapping("/register")
    public String Register(){
        return "register";
    }

    //登录
    @PostMapping("/login")
    @ResponseBody
    public User userLogin(@RequestBody User user) {
        // @RequestBody注解用来绑定通过http请求中application/json类型上传的数据
        System.out.println(user.getMail()+user.getPassword());
        User res=service.Login(user);
        if(res!=null) {
            if (!res.getPassword().equals(user.getPassword())) {
                System.out.println("密码错误");
                return null;
            } else {
                if (!res.equals(users.get(res.getUid())))
                    users.put(res.getUid(), res);
            }
        }
        return res;
    }


    //注册
    @PostMapping("/register")
    @ResponseBody
    public User Register(@RequestBody User user) {
        // @RequestBody注解用来绑定通过http请求中application/json类型上传的数据
        user=service.Register(user);
        return user;
    }

    //进入用户本身的信息主页
    @GetMapping("/{uid}")
    public String userMessage(@PathVariable("uid") Long uid){

        return "success";
    }

    //修改个人信息
    @PostMapping("/change")
    public User Setting(@RequestBody User user){
        boolean succ=service.updateUser(user);
        return succ==true?user:null;
    }

    //退出登录，返回到主页
    @PostMapping("/logout")
    public String userLogout(@RequestBody User user){
        users.remove(user.getUid());
        return "true";
    }

    //注销删号
    @PostMapping("/delete")
    public String userDelete(@RequestBody User user){
        users.remove(user.getUid());
        boolean succ=service.deleteUser(user);
        return succ==true?"":"";
    }


}

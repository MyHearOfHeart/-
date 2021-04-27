package com.springboot.fish.web;

import com.springboot.fish.domain.User;
import com.springboot.fish.domain.Video;
import com.springboot.fish.service.AllService;
import com.springboot.fish.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AllController {
    AllService service=new AllService();

    //搜索，key为关键词,url模式：search?key=...
    @RequestMapping("/search")
    public Map Search(@RequestParam("key") String key){
        //搜索用户
        List<User> users = service.queryUserByName(key);
        //搜索视频(标题)
        List<Video> videos = service.queryVideoByTitle(key);
        Map map = new HashMap();
        map.put("author",users);
        map.put("video",videos);
        return map;
    }

    //搜索
    @GetMapping("/get")
    public Map Search(){
        Map map = new HashMap();
        map.put("author","xiaolou");
        map.put("video","12345");
        return map;
    }

    //进入网页
    @GetMapping("/upfile")
    public String upVideo(){
        return "upfile";
    }

    //上传文件
    @ResponseBody
    @PostMapping("/uploadUrl")
    public String upFile(@RequestParam("file")  MultipartFile file){
        System.out.println(file.getSize());
        CommentService.saveFile(file);
        return null;
    }



}

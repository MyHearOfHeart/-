package com.springboot.fish.web;

import com.springboot.fish.domain.Comment;
import com.springboot.fish.domain.User;
import com.springboot.fish.domain.Video;
import com.springboot.fish.service.AllService;
import com.springboot.fish.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin
@Controller
public class AllController {
    AllService service=new AllService();

    //主页
    @GetMapping("/index")
    public String getIndex(){
        return "index";
    }
    /*
    //搜索，key为关键词,url模式：search?key=...
    @GetMapping("/index")
    public Map Search(@RequestParam("search") String key){
        //搜索用户
        List<User> users = service.queryUserByName(key);
        //搜索视频(标题)
        List<Video> videos = service.queryVideoByTitle(key);
        Map map = new HashMap();
        map.put("author",users);
        map.put("video",videos);
        return map;
    }*/

    //搜索，用于测试
    @GetMapping("/get")
    public Map Search(){
        Map map = new HashMap();
        map.put("author","xiaolou");
        map.put("video","12345");
        return map;
    }

    //进入网页，用于测试
    @GetMapping("/upfile")
    public String upVideo(){
        return "upfile";
    }

    //上传文件，用于网页
    @ResponseBody
    @PostMapping("/upfile")
    public Map upFile(@RequestParam("file")  MultipartFile file,@RequestParam("uid") int uid){
        Map map = new HashMap();
        if (file.isEmpty()){
            System.out.println("文件为空！");
            map.put("result",false);
            return map;
        }
        //保存数据
        CommentService.saveFile(file);
        //插入视频
        Video video = new Video();
        video.setUid(uid);
        video.setTid(1);
        video.setTitle(file.getOriginalFilename());
        video.setText("默认动漫");
        video.setPicture("0");
        video.setVideo("http://localhost:8081/static/video/"+file.getOriginalFilename());
        map.put("result",service.insertVideo(video));
        return map;
    }

    //增加评论和搜索试用
    @PostMapping("/index")
    @ResponseBody
    public Map addComment(@RequestBody Map<String,String> params,@RequestParam("action") String action){
        Map map = new HashMap();
        System.out.println(action);
        //搜索
        if(action.equals("search")){
            //搜索用户
            System.out.println(params.get("keywords"));
            return service.searchAll(params.get("keywords"));
        }
        else if(action.equals("comment")){
            //评论
            int vid = Integer.parseInt(params.get("vid"));
            int uid = Integer.parseInt(params.get("uid"));
            String text = params.get("text");
            String time = params.get("time");
            Comment comment = new Comment();
            comment.setUid(uid);
            comment.setVid(vid);
            comment.setText(text);
            comment.setTime(time);
            boolean succ = service.insertComment(comment);
            map.put("result",succ);
            return map;
        }
        else if(action.equals("getNewVideo")){
            //获取推荐视频
            List<Video> videos = service.queryAllVideos();
            if(videos.size()>=3) {
                map.put("videos", videos.subList(0, 3));
                return map;
            }
            map.put("videos",service.queryAllVideos());
            return map;
        }
        else if(action.equals("getvideo")){
            //获取所有视频
            map.put("videos",service.queryAllVideos());
            return map;
        }
        else if(action.equals("video")){
            //获取视频的作者信息和评论信息
            int vid = Integer.parseInt(params.get("vid"));
            System.out.println(vid);
            //返回的是map类型，包含author，video和comment
            return service.video(vid);
        }
        else if(action.equals("delcomment")){
            //删除评论
            int cid = Integer.parseInt(params.get("cid"));
            service.deleteComment(cid);
        }
        else if(action.equals("change")){
            int uid = Integer.parseInt(params.get("uid"));
            String oldpass = params.get("oldpass");
            String newpass = params.get("newpass");
            User user = service.queryUserByUid(uid);
            if(user.getPassword().equals(oldpass)){
                user.setPassword(newpass);
                map.put("result",service.updateUser(user));
                return map;
            }
            else{
                map.put("result",false);
                return map;
            }
        }
        return map;
    }

    //前端请求文件




}

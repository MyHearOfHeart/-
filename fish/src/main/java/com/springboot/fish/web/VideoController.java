package com.springboot.fish.web;


import com.springboot.fish.domain.Comment;
import com.springboot.fish.domain.User;
import com.springboot.fish.domain.Video;
import com.springboot.fish.service.AllService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/video")
public class VideoController {
    AllService service=new AllService();

    //进入某个视频的界面
    @RequestMapping("/{vid}")
    public Map videoLogin(@PathVariable("vid") Long vid){
        /*
        根据视频id查询视频以及相关评论，还有作者的信息
        返回Json数据
         */
        Video video = service.queryVideoByVid(vid);
        List<Comment> comments=service.queryComment(vid);
        User user = service.queryUserByUid(video.getUid());
        user.setPassword("");
        Map map = new HashMap();
        map.put("author",user);
        map.put("video",video);
        map.put("comment",comments);
        return map;
    }





}

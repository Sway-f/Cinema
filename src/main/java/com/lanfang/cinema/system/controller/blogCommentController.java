package com.lanfang.cinema.system.controller;

import com.lanfang.cinema.system.Domain.User;
import com.lanfang.cinema.system.Domain.blogComment;
import com.lanfang.cinema.system.Service.BlogService;
import com.lanfang.cinema.system.Service.blogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class blogCommentController {
    @Autowired
    blogCommentService blogcommentService;

    @Autowired
    BlogService blogService;

    @PostMapping("/saveblogComment")
    public String saveComment(blogComment blogcomment, HttpSession session, @RequestParam Long blog_id){

        Date date = new Date(System.currentTimeMillis());
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String createdate = sdf.format(date);


        Object o = session.getAttribute("user");
        if(o instanceof User) {
            User u = (User) o;
            System.out.println(u.getUsername());
            blogcomment.setUser_id(u.getId());
            blogcomment.setUser_name(u.getUsername());
        }

        blogcomment.setBlog_id(blog_id);
        int count =blogService.getBlog(blog_id).getReply_count()+1;
        blogService.getBlog(blog_id).setReply_count(count);

        blogcomment.setDay(createdate);

        blogcommentService.saveblogComment(blogcomment);
        return "redirect:/single?blog_id="+blog_id;
    }

}

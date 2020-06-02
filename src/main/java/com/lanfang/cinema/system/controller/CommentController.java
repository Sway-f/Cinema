package com.lanfang.cinema.system.controller;

import com.lanfang.cinema.system.Domain.Comment;
import com.lanfang.cinema.system.Domain.User;
import com.lanfang.cinema.system.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/saveComment")
    public String saveComment(Comment comment, HttpSession session,@RequestParam Long movies_id){

        Date date = new Date(System.currentTimeMillis());
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String createdate = sdf.format(date);


        Object o = session.getAttribute("user");
        if(o instanceof User) {
            User u = (User) o;
            System.out.println(u.getUsername());
            comment.setUser_id(u.getId());
            comment.setUser_name(u.getUsername());
        }

        comment.setMovies_id(movies_id);

        comment.setDay(createdate);
        comment.setReply_count(0);

        commentService.saveComment(comment);
        return "redirect:/movie_select_show?movies_id="+movies_id;
    }
}

package com.lanfang.cinema.system.controller;

import com.lanfang.cinema.system.Domain.Blog;
import com.lanfang.cinema.system.Domain.User;
import com.lanfang.cinema.system.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/saveBlog")
    public String saveBlog(@RequestParam(value = "file") MultipartFile file, Blog blog, HttpSession session){

        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "D://study//idealspace//cinema//src//main//resources//static//images//"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "images/" + fileName;
        System.out.println(filename);
        blog.setImageurl(filename);

        Date date = new Date(System.currentTimeMillis());
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String createdate = sdf.format(date);

        Object o = session.getAttribute("user");
        if(o instanceof User) {
            User u = (User) o;
            System.out.println(u.getUsername());
            blog.setUser_id(u.getId());
            blog.setUser_name(u.getUsername());
        }

        blog.setDay(createdate);
        blog.setReply_count(0);

        blogService.saveBlog(blog);
        return "redirect:/blog";
    }
}

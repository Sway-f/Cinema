package com.lanfang.cinema.system.controller;

import com.lanfang.cinema.system.Dao.UserDao;
import com.lanfang.cinema.system.Domain.User;
import com.lanfang.cinema.system.Service.FriendService;
import com.lanfang.cinema.system.Service.UserService;
import com.lanfang.cinema.system.Util.MD5;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;


    @Autowired
    WebSocketServerController webSocketServerController;

    @RequestMapping("/register")
    public String register(User user){
        user.setImageurl("images/nn.jpg");
        user.setPassword(MD5.code(user.getPassword()));
        userService.save(user);
        return "redirect:/index";
    }

    @PostMapping("/loginto")
    public String loginto(@RequestParam String email, @RequestParam String password, HttpSession session, RedirectAttributes attributes,
                          @RequestParam(value = "url", required = false, defaultValue = "") String url){

        User user = userService.check(email,MD5.code(password));
        if(user !=null){
            user.setPassword(null);
            session.setAttribute("user", user);

            return "redirect:/index?user_id="+user.getId();
        }
        else {
            attributes.addFlashAttribute("message","用户名和密码错误");
            return "redirect:/404";
        }

    }


    @GetMapping("/logout")
    public  String logout(HttpSession session){
        session.removeAttribute("user");
        return  "redirect:/index";
    }

    @PostMapping("/saveuser")
    public String saveuser(User user,@RequestParam(value = "file") MultipartFile file,HttpSession session) throws NotFoundException {
            if (file.isEmpty()) {
                System.out.println("文件为空空");
                user.setImageurl(user.getImageurl());
            }
            else {
                String ff=savefile(file);
                user.setImageurl(ff);
                Object b = session.getAttribute("user");
                if(b instanceof User) {
                    User u = (User) b;
                    friendService.update(u.getId(),ff);
                }


            }

        Object o = session.getAttribute("user");
        if(o instanceof User) {
            User u = (User) o;
            System.out.println(u.getUsername());
            user.setId(u.getId());
            userService.updateUser(u.getId(),user);
            user.setPassword(null);
            session.removeAttribute("user");
            session.setAttribute("user", user);


        }


        return "redirect:/personal?user_id="+user.getId();
    }

    public String savefile( MultipartFile file){


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
            return  filename;

    }



}

package com.lanfang.cinema.system.controller;

import com.lanfang.cinema.system.Domain.Movies;
import com.lanfang.cinema.system.Service.MoviesService;
import com.lanfang.cinema.system.Service.newMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MoviesController {
    @Autowired
    private MoviesService moviesService;

    @RequestMapping("/find")
    public String find(@RequestParam String key , Model model1 ,@PageableDefault(size =6,sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable1){
        System.out.println(key);
        model1.addAttribute("page1",moviesService.search("%"+key+"%",pageable1));

        return "movies";


    }

    @RequestMapping("/findbyterm")
    public String findbyterm(@RequestParam String type ,@RequestParam String city, @RequestParam String day , Model model1 ,@PageableDefault(size =6,sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable1){
        System.out.println(type);
        System.out.println(city);
        System.out.println(day);
//        Session.Cookie cookie =new Session.Cookie();
//        cookie.setName("termName");
//        cookie.setComment(type);
        if(type==""&&city==""&&day==""){
            return "redirect:/allmovies";
        }
        model1.addAttribute("page1",moviesService.findbyterm("%"+type+"%","%"+city+"%","%"+day+"%",pageable1));
        pageable1.toString();

        return "movies";


    }


}

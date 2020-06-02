package com.lanfang.cinema.system.controller;

import com.lanfang.cinema.system.Domain.Friend;
import com.lanfang.cinema.system.Domain.Movies;
import com.lanfang.cinema.system.Domain.Re;
import com.lanfang.cinema.system.Domain.User;
import com.lanfang.cinema.system.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class SystemConroller {
    @Autowired
    private UserService userService;

    @Autowired
    private MoviesService moviesService;

    @Autowired
    private newMoviesService newmoviesService;

    @Autowired
    private topMoviesService topmoviesService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private blogCommentService blogCommentService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private ReService reService;

    @GetMapping("/support")
    public String support(){
        return "support";
    }

    @GetMapping("/movie_select_show")
    public String select(@RequestParam(value = "movies_id", required = false, defaultValue = "") Long movies_id, Model model1,
                         @PageableDefault(size =6) Pageable pageable1){
        System.out.println(movies_id);
        model1.addAttribute("movies",moviesService.getMovies(movies_id));
        model1.addAttribute("page",commentService.getCommentByMovies_id(movies_id,pageable1));
        return "movie_select_show";
    }
    @GetMapping("/")
    public String index(@PageableDefault(size =3,sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable1, Model model1,
                        HttpSession session) {
        model1.addAttribute("page1",moviesService.listMovies(pageable1));
        model1.addAttribute("page2",moviesService.remen());
        model1.addAttribute("list1",newmoviesService.sildernewMovies());
        model1.addAttribute("top",topmoviesService.sildertopMovies());

        Object o = session.getAttribute("user");
        User u = new User();
        if(o instanceof User) {
            u = (User) o;
            System.out.println(u.getUsername());
        }
        if(u.getId()!=null){

            List<Re> r =reService.getRecommend(u.getId());
            List<Movies> j = new ArrayList<>() ;
            sign:
            for(int i=0;i<r.size();i++){
                for(int m = 0;m<j.size();m++){
                    if(j.get(m).getName().equals( moviesService.getMovies(r.get(i).getMovie_id()).getName())){
                        continue sign;
                    }
                }
                j.add( moviesService.getMovies(r.get(i).getMovie_id()));
            }
            System.out.println(j.size());

            model1.addAttribute("re",j);
        }
        return "index";
    }

    @GetMapping("/allmovies")
    public String movies( @PageableDefault(size =24,sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable1, Model model1) {
        model1.addAttribute("page1",moviesService.listMovies(pageable1));
        return "movies";
    }

    @GetMapping("/index")
    public String index_2(@PageableDefault(size =3,sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable1,
                          Model model1, HttpSession session) {
        model1.addAttribute("page1",moviesService.listMovies(pageable1));
        model1.addAttribute("page2",moviesService.remen());
        model1.addAttribute("list1",newmoviesService.sildernewMovies());
        model1.addAttribute("top",topmoviesService.sildertopMovies());

        Object o = session.getAttribute("user");
        User u = new User();
        if(o instanceof User) {
            u = (User) o;
            System.out.println(u.getUsername());
        }
        if(u.getId()!=null){

            List<Re> r =reService.getRecommend(u.getId());
            List<Movies> j = new ArrayList<>() ;
            sign:
            for(int i=0;i<r.size();i++){
                for(int m = 0;m<j.size();m++){
                    if(j.get(m).getName().equals( moviesService.getMovies(r.get(i).getMovie_id()).getName())){
                        continue sign;
                    }
                }
                j.add( moviesService.getMovies(r.get(i).getMovie_id()));
            }
            System.out.println(j.size());

            model1.addAttribute("re",j);
        }


        return "index";
    }


    @GetMapping("/blog")
    public String blog(@PageableDefault(size =8,sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable1, Model model1) {
        model1.addAttribute("page1",blogService.listBlog(pageable1));
        return "blog";
    }

    @GetMapping("/single")
    public String single(@RequestParam(value = "blog_id", required = false, defaultValue = "") Long blog_id,
                         @PageableDefault(size =8,sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable1,
                         Model model1 , Model model2) {
        model1.addAttribute("blog",blogService.getBlog(blog_id));
        model2.addAttribute("blogcomment",blogCommentService.getblogCommentByblog_id(blog_id,pageable1));

        return "single";
    }

    @PostMapping("/chat")
    public String chat(@RequestParam(value = "user_id", required = false, defaultValue = "") Long user_id,
                       Friend friend, @PageableDefault(size =20,sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable1,
                       Model model) {
        if(friendService.findExit(user_id,friend.getF_id())!=null){
            model.addAttribute("friend",friendService.getFriendByUser_id(user_id,pageable1));

            model.addAttribute( "1",friendService.getFriendByUser_id(user_id,pageable1));
            model.addAttribute("one",userService.getUser(friend.getF_id()));
            model.addAttribute("record",recordService.getRecord(user_id,friend.getF_id(),pageable1));
            return "chat";
        }
        else{
            friend.setF_image(userService.getUser(friend.getF_id()).getImageurl());
            friendService.saveFriend(friend);
            model.addAttribute("friend",friendService.getFriendByUser_id(user_id,pageable1));
            model.addAttribute("one",userService.getUser(friend.getF_id()));
            model.addAttribute("record",recordService.getAll(user_id,pageable1));

            return "chat";
        }

    }

    @PostMapping("/change")
    public String change(@RequestParam(value = "user_id", required = false, defaultValue = "") Long user_id,
                         @RequestParam(value = "f_id", required = false, defaultValue = "") Long f_id,
                         @PageableDefault(size =100,sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable1,
                         Model model){
        model.addAttribute("friend",friendService.getFriendByUser_id(user_id,pageable1));
        model.addAttribute("one",userService.getUser(f_id));
        model.addAttribute("record",recordService.getRecord(user_id,f_id,pageable1));
        return "chat";
    }

    @GetMapping("/chatlist")
    public String chatlist(@RequestParam(value = "user_id", required = false, defaultValue = "") Long user_id,
                         @PageableDefault(size =100,sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable1,
                         Model model){
        model.addAttribute("friend",friendService.getFriendByUser_id(user_id,pageable1));
        return "chat";
    }

    @GetMapping("/about")
    public String about( ) {
        return "about";
    }



    @GetMapping("/contact")
    public String contact( ) {
        return "contact";
    }

    @GetMapping("/faq")
    public String faq( ) {
        return "faq";
    }

    @GetMapping("/press")
    public String press( ) {
        return "press";
    }

    @GetMapping("/public-relations")
    public String public_relations( ) {
        return "public-relations";
    }


    @GetMapping("/sports")
    public String sports( ) {
        return "sports";
    }

    @GetMapping("/plays")
    public String plays( ) {
        return "plays";
    }

    @GetMapping("/events")
    public String events( ) {
        return "events";
    }

    @GetMapping("/personal")
    public String personal(@RequestParam(value = "user_id", required = false, defaultValue = "") Long user_id,
                           @PageableDefault(size =8,sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable1,
                           Model model1) {
        model1.addAttribute("page1",blogService.getBlogByUser_id(user_id,pageable1));
        model1.addAttribute("user",userService.getUser(user_id));
        return "personal";
    }



    @GetMapping("/movie_single")
    public String movie_single( ) {
        return "movie-single";
    }

    @GetMapping("/socket")
    public String socket( ) {
        return "chat";
    }

    @GetMapping("/sentblog")
    public String sentblog( ) {
        return "sentblog";
    }
    @GetMapping("/event-payment")
    public String event_payment( ) {
        return "event-payment";
    }



    public void re(Re re) {
        reService.saveRe(re);
    }



}


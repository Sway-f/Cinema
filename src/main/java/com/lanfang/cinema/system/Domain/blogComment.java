package com.lanfang.cinema.system.Domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="blogcomment")
public class blogComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //博客id
    private Long blog_id;

    //回复用户id
    private  Long user_id;

    //回复用户名字
    private String user_name;

    //评论时间
    private String day;

    //回复内容
    private String content;

    public blogComment(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(Long blog_id) {
        this.blog_id = blog_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

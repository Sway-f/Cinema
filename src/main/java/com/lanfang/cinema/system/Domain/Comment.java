package com.lanfang.cinema.system.Domain;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name ="comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //电影id
    private Long movies_id;

    //用户id
    private  Long user_id;

    //用户名字
    private String user_name;

    //用户评分
    private int score;

    //评论时间
    private String day;

    //回复数量
    private int reply_count;

    //回复内容
    private String content;

    public Comment(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getMovies_id() {
        return movies_id;
    }

    public void setMovies_id(Long movies_id) {
        this.movies_id = movies_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getReply_count() {
        return reply_count;
    }

    public void setReply_count(int reply_count) {
        this.reply_count = reply_count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

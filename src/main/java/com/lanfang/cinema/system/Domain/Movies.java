package com.lanfang.cinema.system.Domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="movies")

public class Movies implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //电影名称
    private String name;
    //上映年份
    private String dateline;
    //地区
    private String city;
    //评分
    private double score;
    //导演
    private String director;
    //演员
    private String actor;
    //简介
    private String theme;
    //统计
    private Long count;
    //图片地址
    private  String imageurl;
    //电影id
    private  Long movies_id;
    //评价人数
    private Long number;
    //类型
    private String type;
    //编剧
    private  String writer;
    //语言
    private String language;
    //时长
    private String time;
    //视频链接
    private String imburl;

    public Movies(){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getCity() {
        return city;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Long getMovies_id() {
        return movies_id;
    }

    public void setMovies_id(Long movies_id) {
        this.movies_id = movies_id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImburl() {
        return imburl;
    }

    public void setImburl(String imburl) {
        this.imburl = imburl;
    }
}

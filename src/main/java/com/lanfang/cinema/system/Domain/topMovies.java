package com.lanfang.cinema.system.Domain;


import javax.persistence.*;

@Entity
@Table(name ="top_movies")
public class topMovies {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //电影名称
    private String movies_name;
    //上映年份
    private String movies_day;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovies_name() {
        return movies_name;
    }

    public void setMovies_name(String movies_name) {
        this.movies_name = movies_name;
    }

    public String getMovies_day() {
        return movies_day;
    }

    public void setMovies_day(String movies_day) {
        this.movies_day = movies_day;
    }

    public String getCity() {
        return city;
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

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Long getMovies_id() {
        return movies_id;
    }

    public void setMovies_id(Long movies_id) {
        this.movies_id = movies_id;
    }
}

package com.lanfang.cinema.system.Domain;


import javax.persistence.*;

@Entity
@Table(name ="friend")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //通讯录id
    private Long user_id;
    //用户id
    private String user_name;
    //用户昵称
    private Long f_id;
    //好友id
    private String f_name;
    //好友昵称
    private String f_image;
    //好友头像


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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Long getF_id() {
        return f_id;
    }

    public void setF_id(Long f_id) {
        this.f_id = f_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getF_image() {
        return f_image;
    }

    public void setF_image(String f_image) {
        this.f_image = f_image;
    }
}

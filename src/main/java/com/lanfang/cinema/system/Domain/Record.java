package com.lanfang.cinema.system.Domain;

import javax.persistence.*;

@Entity
@Table(name ="record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //聊天记录id

    private Long user_id;
    //发送者id

    private String user_name;
    //发送者名字

    private Long f_id;
    //接收者id

    private String f_name;
    //接收者名字

    private String message;
    //消息内容

    private String time;
    //发送时间

    private int me;

    public int getMe() {
        return me;
    }

    public void setMe(int me) {
        this.me = me;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

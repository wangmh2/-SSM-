package com.demo.entity;

import java.sql.Timestamp;

public class Message {
    private String username;
    private String info;
    private Timestamp createtime;
    
    public Message(){
        
    }

    public Message(String username, String info, Timestamp createtime) {
        this.username = username;
        this.info = info;
        this.createtime = createtime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "useremail='" + username + '\'' +
                ", info='" + info + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}

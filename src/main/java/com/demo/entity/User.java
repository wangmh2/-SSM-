package com.demo.entity;

import java.sql.Timestamp;

public class User {
    private String username;
    private String password;
    private String phonenumber;
    private Timestamp createtime;
    
    public User(){
        
    }

    public User(String username, String password, String phonenumber, Timestamp createtime) {
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.createtime = createtime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}

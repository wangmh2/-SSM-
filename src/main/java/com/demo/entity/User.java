package com.demo.entity;

import java.sql.Timestamp;

public class User {
    private String useremail;
    private String password;
    private String password_protection;
    private Timestamp createtime;
    private int state;

    public User(String useremail, String password, String password_protection, Timestamp createtime, int state) {
        this.useremail = useremail;
        this.password = password;
        this.password_protection = password_protection;
        this.createtime = createtime;
        this.state = state;
    }
    
    public User(){
        
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_protection() {
        return password_protection;
    }

    public void setPassword_protection(String password_protection) {
        this.password_protection = password_protection;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "useremail='" + useremail + '\'' +
                ", password='" + password + '\'' +
                ", password_protection='" + password_protection + '\'' +
                ", createtime=" + createtime +
                ", state=" + state +
                '}';
    }
}

package com.demo.entity;

import java.sql.Timestamp;

public class Message {
    private String useremail;
    private String info;
    private Timestamp createtime;
    
    public Message(){
        
    }

    public Message(String useremail, String info, Timestamp createtime) {
        this.useremail = useremail;
        this.info = info;
        this.createtime = createtime;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
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
                "useremail='" + useremail + '\'' +
                ", info='" + info + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}

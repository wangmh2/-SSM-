package com.demo.entity;

import java.sql.Timestamp;

public class beiwang {
    private String username;
    private Timestamp beiwangtime;
    private String address;
    
    public beiwang(){
        
    }

    public beiwang(String username, Timestamp beiwangtime, String address) {
        this.username = username;
        this.beiwangtime = beiwangtime;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getBeiwangtime() {
        return beiwangtime;
    }

    public void setBeiwangtime(Timestamp beiwangtime) {
        this.beiwangtime = beiwangtime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "beiwang{" +
                "username='" + username + '\'' +
                ", beiwangtime='" + beiwangtime + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

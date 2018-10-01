package com.demo.service;

import com.demo.entity.User;

public interface UserService {
    //根据邮箱来查询用户
    public User queryUserbyphonenumber(String phonenumber) throws Exception;

    //添加用户
    public void adduser(User user) throws Exception;

    //更改用户的密码
    public void updatepassword(String newpassword, String phonenumber) throws Exception;

    //根据用户的用户名来查询用户
    public User querybyname(String username) throws Exception;
}

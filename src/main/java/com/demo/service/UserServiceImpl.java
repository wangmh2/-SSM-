package com.demo.service;

import com.demo.entity.User;

import com.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper um;


    @Override
    public User queryUserbyphonenumber(String phonenumber) throws Exception {
        User user = um.queryUserbyphonenumber(phonenumber);
        return user;
    }

    @Override
    public void adduser(User user) throws Exception {
        um.adduser(user);
    }

    @Override
    public void updatepassword(String newpassword, String phonenumber) throws Exception {
        um.updatepassword(newpassword,phonenumber);
    }

    @Override
    public User querybyname(String username) throws Exception {
        User user = um.querybyname(username);
        return user;
    }
}

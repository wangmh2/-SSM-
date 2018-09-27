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
    public User queryUserbyemail(String useremail) throws Exception {
        User user = um.queryUserbyemail(useremail);
        return user;
    }

    @Override
    public void adduser(User user) throws Exception {
        um.adduser(user);
    }

    @Override
    public void updatepassword(String newpassword, String useremail) throws Exception {
        um.updatepassword(newpassword,useremail);
    }

    @Override
    public User querybycode(String code) throws Exception {
        User user = um.querybycode(code);
        return user;
    }

    @Override
    public void updatestate(String useremail) {
        um.updatestate(useremail);
    }
}

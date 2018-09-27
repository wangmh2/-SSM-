package com.demo.service;

import com.demo.entity.User;

public interface UserService {
    //根据手机号码来查询用户
    public User queryUserbyemail(String useremail) throws Exception;
    
    //添加用户
    public void adduser(User user) throws Exception;
    
    //修改用户密码
    public void updatepassword(String newpassword,String useremail) throws Exception;
    
    //根据code来查询用户
    public User querybycode(String code) throws Exception;
    
    public void updatestate(String useremail);
}

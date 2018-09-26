package com.demo.mapper;


import com.demo.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    //根据手机号码来查询用户
    public User queryUserbyemail(String useremail) throws Exception;
    
    //添加用户
    public void adduser(User user) throws Exception;
    
    //更改用户的密码
    public void updatepassword(String newpassword, String useremail) throws Exception;
    
    //根据code来查询用户
    public User querybycode(String code) throws Exception;
    
    //更改用户的state
    public void updatestate(String useremail);
    
}

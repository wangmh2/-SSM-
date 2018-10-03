package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.UserServiceImpl;
import com.demo.Utils.Timetools;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController {

    @Autowired
    private UserServiceImpl usi;
    @Autowired
    private Producer captchaProducer = null;

    //跳转至登录界面
    @RequestMapping("login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    //跳转至注册界面
    @RequestMapping("resigter")
    public String resigter() {
        return "resigter";
    }

    //跳转至忘记密码界面
    @RequestMapping("forgetpassword")
    public String forgetpassword(){
        return "forgetpassword";
    }

    //跳转至首页
    @RequestMapping("/test.html")
    public String homepage(){
        return "test";
    }

    @RequestMapping("userpage")
    public String chat(){return "userpage";}


    //登录控制
    @ResponseBody
    @RequestMapping(value = "userlogin", method = RequestMethod.POST)
    public Map<String,Object> userlogin(String phonenumber,String password,String vcode,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        response.setContentType("text/html;charset=utf-8");
        
        if (vcode.equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))) {
            User user = usi.queryUserbyphonenumber(phonenumber);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    //将用户名放入session中
                    Timetools tt = new Timetools();
                    String timeq = tt.time();
                    request.getSession().setAttribute("time",timeq);
                    request.getSession().setAttribute("username",user.getUsername());
                    //登录成功  状态码0
                    map.put("code","0");
                } else {
                    //密码错误  状态码1
                    map.put("code","1");
                }
            }else{
                //未注册  用户不存在   状态码2
                map.put("code","2");
            }
        }else{
            //验证码错误  状态码3
            map.put("code","3");
        }

        return map;
    }

    //注册控制
    @ResponseBody
    @RequestMapping(value = "userresigter",method = RequestMethod.POST)
    public Map<String,Object> userresigter(String phonenumber,String password,String username,String yanzhengma,
                                           HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        System.out.println("前端传来的验证码是"+yanzhengma);
        System.out.println("前端传来的手机号码是"+phonenumber);
        System.out.println("前端传来的用户名是"+username);
        System.out.println("前段传来的密码是"+password);
        Date date = new Date();
        Timestamp createtime = new Timestamp(date.getTime());
        User user = usi.queryUserbyphonenumber(phonenumber);
        if(request.getSession().getAttribute("phonevcode").equals(yanzhengma)){
            if(user == null){
                //确认添加用户
                User user1 = new User(username,password,phonenumber,createtime);
                usi.adduser(user1);
                request.getSession().setAttribute("userphone",phonenumber);
                request.getSession().setAttribute("password",password);
                map.put("code","0");
            }else{
                //该手机号码已经被注册  状态码2
                map.put("code","2");
            }    
        }else{
            //手机验证码不正确   状态码1
            map.put("code","1");
        }
        return map;
    }

    //更改密码
    @ResponseBody
    @RequestMapping(value = "changepassword",method = RequestMethod.POST)
    public Map<String,Object> changepassword(String phonenumber,String newpassword,String yanzhengma,
            HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        User user = usi.queryUserbyphonenumber(phonenumber);
        if(user != null){
            if(request.getSession().getAttribute("phonevcode1").equals(yanzhengma)){
                usi.updatepassword(newpassword,phonenumber);
                request.getSession().setAttribute("userphone",phonenumber);
                request.getSession().setAttribute("password",newpassword);
                //更改密码成功  状态码1
                map.put("code","1");
            }else {
                //手机验证码错误   状态码2
                map.put("code","2");
            }
        }else {
            //没有此用户   状态码0
            map.put("code","0");
        }
        return map;
    }
}

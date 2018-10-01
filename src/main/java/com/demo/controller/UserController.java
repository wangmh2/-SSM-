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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;


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

    @RequestMapping("chat")
    public String chat(){return "chat";}


    //登录控制
    @RequestMapping(value = "userlogin", method = RequestMethod.GET)
    public ModelAndView userlogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //用户输入的邮箱
        String phonenumber = request.getParameter("phonenumber");

        //用户输入的密码
        String password = request.getParameter("password");

        //用户输入的验证码
        String vcode = request.getParameter("vcode");

        ModelAndView mav = new ModelAndView();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (vcode.equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))) {
            User user = usi.queryUserbyphonenumber(phonenumber);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    //将用户名放入session中
                    Timetools tt = new Timetools();
                    String timeq = tt.time();
                    request.getSession().setAttribute("time",timeq);
                    request.getSession().setAttribute("username",user.getUsername());
                    mav.setViewName("userpage");
                } else {
                    mav.addObject("message","您的密码错误");
                    mav.setViewName("login");
                }
            }else{
                mav.addObject("message","该用户不存在");
                mav.setViewName("login");
            }
        }else{
            mav.addObject("message","验证码错误");
            mav.setViewName("login");
        }

        return mav;
    }

    //注册控制
    @RequestMapping("userresigter")
    public ModelAndView userresigter(HttpServletRequest request,HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        String username = (String)request.getParameter("username");
        System.out.println("前端传来的username是:"+username);
        String password = (String)request.getParameter("password");
        System.out.println("前端出来的password是:"+password);
        String phonenumber = (String)request.getParameter("phonenumber");
        System.out.println("前端传来的phonenumber是:"+phonenumber);
        String yanzhengma = (String)request.getParameter("yanzhengma");
        System.out.println("前端传来的验证码是:"+yanzhengma);
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
                mav.setViewName("login");
                System.out.println("已经执行到这里了");
            }else{
                mav.addObject("message","该手机号码已经被注册");
                mav.setViewName("resigter");
            }    
        }else{
            mav.addObject("message","您的手机验证码不正确");
            mav.setViewName("resigter");
        }
        System.out.println("执行到这了吗？");
        return mav;
    }

    //更改密码
    @RequestMapping(value = "changepassword",method = RequestMethod.GET)
    public ModelAndView changepassword(HttpServletRequest request,HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        String phonenumber = request.getParameter("phonenumber");
        String newpassword = request.getParameter("newpassword");
        String password_protection = request.getParameter("password_protection");
        User user = usi.queryUserbyphonenumber(phonenumber);
        if(user != null){
            
        }else{
            mav.addObject("message","该邮箱账号不存在");
            mav.setViewName("forgetpassword");
        }

        return mav;
    }
}

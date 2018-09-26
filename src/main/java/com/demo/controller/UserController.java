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
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //跳转至注册界面
    @RequestMapping("/resigter")
    public String resigter() {
        return "resigter";
    }

    //跳转至忘记密码界面
    @RequestMapping("/forgetpassword")
    public String forgetpassword(){
        return "forgetpassword";
    }

    //跳转至首页
    @RequestMapping("/test.html")
    public String homepage(){
        return "test";
    }

    @RequestMapping("/chat")
    public String chat(){return "chat";}


    //登录控制
    @RequestMapping(value = "/userlogin", method = RequestMethod.GET)
    public ModelAndView userlogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //用户输入的邮箱
        String useremail = request.getParameter("useremail");

        //用户输入的密码
        String password = request.getParameter("password");

        //用户输入的验证码
        String vcode = request.getParameter("vcode");

        ModelAndView mav = new ModelAndView();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (vcode.equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))) {
            User user = usi.queryuserbyemail(useremail);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    //将用户邮箱放入session中
                    Timetools tt = new Timetools();
                    String timeq = tt.time();
                    request.getSession().setAttribute("time",timeq);
                    request.getSession().setAttribute("useremail",useremail);
                    mav.setViewName("/userpage");
                } else {
                    mav.addObject("message","您的密码错误");
                    mav.setViewName("/login");
                }
            }else{
                mav.addObject("message","该用户不存在");
                mav.setViewName("/login");
            }
        }else{
            mav.addObject("message","验证码错误");
            mav.setViewName("/login");
        }

        return mav;
    }

    //注册控制
    @RequestMapping(value = "/userresigter", method = RequestMethod.GET)
    public ModelAndView userresigter(HttpServletRequest request,HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        String useremail = request.getParameter("useremail");
        String password = request.getParameter("password");
        String password_protection = request.getParameter("password_protection");
        Date date = new Date();
        Timestamp createtime = new Timestamp(date.getTime());
        User user = usi.queryuserbyemail(useremail);
        if(user == null){
            //确认添加用户
            User user1 = new User(useremail,password,password_protection,createtime,0);
            usi.adduser(user1);
            mav.addObject("useremail",useremail);
            mav.addObject("password",password);
            mav.setViewName("/login");
        }else{
            mav.addObject("message","该邮箱已经被注册");
            mav.setViewName("/resigter");
        }
        return mav;
    }

    //更改密码
    @RequestMapping(value = "/changepassword",method = RequestMethod.GET)
    public ModelAndView changepassword(HttpServletRequest request,HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        String useremail = request.getParameter("useremail");
        String newpassword = request.getParameter("newpassword");
        String password_protection = request.getParameter("password_protection");
        User user = usi.queryuserbyemail(useremail);
        if(user != null){
            if(password_protection.equals(user.getPassword_protection())){
                usi.updatepassword(newpassword,useremail);
                User user1 = usi.queryuserbyemail(useremail);
                System.out.println(user1);
                mav.addObject("useremail",useremail);
                mav.addObject("password",newpassword);
                mav.setViewName("/login");
            }else{
                mav.addObject("message","您的密保号不正确");
                mav.setViewName("/forgetpassword");
            }
        }else{
            mav.addObject("message","该邮箱账号不存在");
            mav.setViewName("/forgetpassword");
        }

        return mav;
    }
}

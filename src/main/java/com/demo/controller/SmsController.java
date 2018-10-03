package com.demo.controller;


import com.demo.Utils.SmsUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SmsController {
    //注册时发送验证码
    @RequestMapping(value = "sendcode",method = RequestMethod.GET)
    public ModelAndView sendcode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        String phonenumber = request.getParameter("phonenumber");
        System.out.println("phonenumber="+phonenumber);
        //生成一个六位数的随机验证码
        int randomnumber = (int)((Math.random()*9+1)*100000);
        //将int转String
        String s = String.valueOf(randomnumber);
        System.out.println("注册验证码是"+s);
        //将验证码放进session中方便注册时验证
        request.getSession().setAttribute("phonevcode",s);
        SmsUtils.SendCode(phonenumber,s);
        mav.addObject("message","您的验证码已发送，请注意查收");
        mav.setViewName("resigter");
        return mav;
    }

    //更改密码时发送验证码
    @RequestMapping(value = "sendcode1",method = RequestMethod.GET)
    public ModelAndView sendcode1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        String phonenumber = request.getParameter("phonenumber");
        System.out.println("phonenumber="+phonenumber);
        //生成一个六位数的随机验证码
        int randomnumber = (int)((Math.random()*9+1)*100000);
        //将int转String
        String s = String.valueOf(randomnumber);
        System.out.println("更改密码的验证码是"+s);
        request.getSession().setAttribute("phonevcode1",s);
        SmsUtils.SendCode(phonenumber,s);
        mav.addObject("message","您的验证码已发送，请注意查收");
        mav.setViewName("forgetpassword");
        return mav;
    }
}

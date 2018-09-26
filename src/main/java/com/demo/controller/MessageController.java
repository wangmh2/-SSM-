package com.demo.controller;

import com.demo.entity.Message;
import com.demo.service.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MessageController {
    @Autowired
    private MessageServiceImpl mmi;
    //增加留言
    @RequestMapping(value = "/addmessage",method = RequestMethod.GET)
    public ModelAndView addmessage(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView();
        String info = request.getParameter("info");
        String useremail = request.getParameter("useremail");
        Date date = new Date();
        Timestamp createtime = new Timestamp(date.getTime());
        Message message = new Message(useremail,info,createtime);
        //向数据库中增加一条留言
        mmi.addmessage(message);
        //当前总数据条数和总页数并放入session中
        int num = mmi.queryconut();
        int pagenum = 0;
        if(num % 5 != 0){
            pagenum = num / 5 + 1;
        }else{
            pagenum = num / 5;
        }
        request.getSession().setAttribute("pagenum",pagenum);
        System.out.println("pagenum"+pagenum);
        List<Message> list = new ArrayList();
        list = mmi.querymessage(1);
        for(Message i : list){
            System.out.println(i);
        }
        mav.addObject("message1",list);
        mav.setViewName("/liuyanban");
        return mav;
    }
    //第一次进入时初始化留言板
    @RequestMapping("/message")
    public ModelAndView liuyanban(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mav = new ModelAndView();
        int pageno1 = 1;
        //查询出当前页面的五条数据
        List<Message> message = new ArrayList();
        //当前总数据条数
        int num = mmi.queryconut();
        System.out.println("当前数据库中共有"+num+"条数据");
        int pagenum = 0;
        if(num % 5 != 0){
            pagenum = num / 5 + 1;
        }else{
            pagenum = num / 5;
        }
        System.out.println("当前数据库中共有"+pagenum+"页数据");
        request.getSession().setAttribute("pagenum",pagenum);
        int dangqianpageno = 1;
        request.getSession().setAttribute("dangqianpageno",dangqianpageno);
        System.out.println("当前的页码是"+dangqianpageno);
        message = mmi.querymessage(pageno1);
       
        mav.addObject("message1",message);
        mav.setViewName("/liuyanban");
        return mav;
    }
    //改变页数
    @RequestMapping(value = "/changemessage",method = RequestMethod.GET)
    public ModelAndView changemessage(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mav = new ModelAndView();
        String pageno = request.getParameter("pageno");
        int pageno1 = Integer.parseInt(pageno);
        System.out.println("传回来的参数pageno是"+pageno1);
        request.getSession().setAttribute("dangqianpageno",pageno1);
        System.out.println("当前页是第"+pageno1+"页");
        List<Message> message = new ArrayList();
        message = mmi.querymessage(pageno1);
        mav.addObject("message1",message);
        mav.setViewName("/liuyanban");
        return mav;
    }
}

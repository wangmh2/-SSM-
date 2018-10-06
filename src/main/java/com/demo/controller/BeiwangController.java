package com.demo.controller;

import com.demo.entity.beiwang;
import com.demo.service.BeiwangServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BeiwangController {
    @Autowired
    private BeiwangServiceImpl bsi;
    
    @ResponseBody
    @RequestMapping(value = "addbeiwang",method = RequestMethod.POST)
    public Map<String,Object> addbeiwang(Timestamp beiwangtime, String address,
                                         HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();
        String username = (String)request.getSession().getAttribute("username");
        beiwang beiwang = new beiwang(username,beiwangtime,address);
        bsi.addbeiwang(beiwang);
        return map;
    }
}

package com.demo.controller;



import com.demo.entity.DanMu;
import com.demo.service.DanMuService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 *
 * @author xiaosuda
 * @date 2018/5/18
 */
@Controller
public class DanMuController {

    private final String APPKEY = "BC-8f96d223eae243aab603a9c541fcca88";

    @Autowired
    private DanMuService danMuService;
    @RequestMapping(value = "adddanmu", method = RequestMethod.POST)
    @ResponseBody
    public void add(String msg, String time){
        System.out.println("弹幕消息是:"+msg);
        GoEasy goEasy = new GoEasy(APPKEY);
        //使用goeasy第三方推送服务 向相同频道的其它用户推送消息
        goEasy.publish("DanMu", msg);
        //将弹幕内容和时间保存到数据库
        DanMu danmu = new DanMu(msg,time);
        danMuService.add(danmu);
    }

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<DanMu> findAll() {
        List<DanMu> all = danMuService.findAll();
        return all;
    }
}

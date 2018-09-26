package com.demo.service;

import com.demo.entity.Message;

import java.util.List;

public interface MessageService {
    //添加留言
    public void addmessage(Message message);

    //每次查询五条留言
    public List<Message> querymessage(int pageno);
    
    //查询当前总共有多少条数据记录
    public int queryconut();
}

package com.demo.mapper;

import com.demo.entity.Message;

import java.util.List;

public interface MessageMapper {
    //添加留言
    public void addmessage(Message message);
    
    //每次查询五条留言
    public List<Message> querymessage(int pageno);
    
    //返回数据库中的记录条数
    public int queryconut();
}

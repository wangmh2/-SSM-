package com.demo.service;

import com.demo.entity.Message;
import com.demo.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageMapper mm;
    @Override
    public void addmessage(Message message) {
        mm.addmessage(message);
    }

    @Override
    public List<Message> querymessage(int pageno) {
        int pageno1 = pageno*5-5;
        List<Message> me = mm.querymessage(pageno1);
        return me;
    }


    @Override
    public int queryconut() {
        int i = mm.queryconut();
        return i;
    }
}

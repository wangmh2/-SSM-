package com.demo.test;

import java.sql.Timestamp;
import java.util.Date;
//获取当前系统的时间测试
public class Test {
    private Timestamp createtime;
    public static void main(String []args){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        System.out.println(timestamp);
    }
}

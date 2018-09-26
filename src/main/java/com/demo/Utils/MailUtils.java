package com.demo.Utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

//邮箱工具类
public class MailUtils implements Runnable{
    //用户邮箱
    private String useremail;
    //验证码
    private String code;

    public MailUtils(String useremail, String code) {
        this.useremail = useremail;
        this.code = code;
    }

    @Override
    public void run() {
        //配置邮箱 
        Properties prop = new Properties();
        //设置邮箱服务器主机名
        prop.put("mail.host","smtp.163.com");
        //发送邮件协议名称
        prop.put("mail.transport.protocal","smtp");
        // 是否认证
        prop.put("mail.smtp.auth", true);
        // 创建会话对象
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            // 认证信息，需要提供"用户账号","授权码"
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("wmh1103574375@163.com", "wmh19960621");
            }
        });
        // 是否打印出debug信息
        session.setDebug(true);

        // 创建邮件
        Message message = new MimeMessage(session);
        // 邮件发送者
        try {
            message.setFrom(new InternetAddress("wmh1103574375@163.com"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 邮件接受者
        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(useremail));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 邮件主题
        try {
            message.setSubject("用户激活邮件");
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }
        String content = "<html><head></head><body><h1>请点击连接激活</h1><h3><a href='http://localhost:8080/active?code="
                + code + "'>http://localhost:8080/active?code=" + code + "</href></h3></body></html>";
        try {
            message.setContent(content, "text/html;charset=UTF-8");
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }
        // Transport.send(message);
        // 邮件发送
        Transport transport = null;
        try {
            transport = session.getTransport();
        } catch (NoSuchProviderException e1) {
            e1.printStackTrace();
        }
        try {
            transport.connect();
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }
        try {
            transport.sendMessage(message, message.getAllRecipients());
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }
        try {
            transport.close();
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }
    } 
    
    
}

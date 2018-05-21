package com.cxy.email;

import com.cxy.aliyun.AliyunApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AliyunApplication.class)
public class EmailTest {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void send() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("328108223@qq.com");
        message.setTo("cxyzjp@foxmail.com");
        message.setSubject("test simple mail");
        message.setText("hello this is simple mail");

        try {
            mailSender.send(message);
            System.out.println("简单邮件已经发送。");
        } catch (Exception e) {
            System.out.println("发送简单邮件时发生异常！");
            e.printStackTrace();
        }
    }

}

package com.cxy.email;

import com.cxy.aliyun.AliyunApplication;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AliyunApplication.class)
public class EmailTest {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private MailProperties email;

    @Test
    public void send() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dev_rad@fih-foxconn.com.cn");
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

    @Test
    public void sendTemplateMail(){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("328108223@qq.com");
            helper.setTo("cxyzjp@foxmail.com");
            helper.setSubject("主题：模板邮件");

            Map<String, Object> model = new HashMap<>();
            model.put("username", "zhang");

            //读取 html 模板
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("message.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(html, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void get() {
        System.out.println(email.getUsername());
    }
}

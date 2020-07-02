package com.rul.blog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.from.addr}")
    private String from;

    public boolean sendEmail(String to, String verifyCode) {
        try {
            //实例化SimpleMailMessage对象
            SimpleMailMessage message = new SimpleMailMessage();

            //设置邮件标题，内容，发送方，接收方
            message.setSubject("RuL-Blog注册验证码");
            message.setText("您本次的验证码为：" + verifyCode + "，十分钟内有效，如非本人操作，请忽略！");
            message.setFrom(from);
            message.setTo(to);

            //使用JavaMailSender发送邮件
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 生成随机验证码
     *
     * @return 随机生成的6位数验证码
     */
    public String randomVerifyCode() {
        int randomNumber = new Random().nextInt(900000) + 100000;
        return randomNumber + "";
    }
}

package com.rul.blog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EmailUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

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
            LOGGER.info("send email to {} successful",to);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("fail to send email to {}",to);
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

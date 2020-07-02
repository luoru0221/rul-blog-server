package com.rul.blog.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class EmailUtilTest {
    @Autowired
    private EmailUtil emailUtil;

    @Test
    void sendEmailTest(){
        emailUtil.sendEmail("luoru0221@gmail.com","123456");
    }
}

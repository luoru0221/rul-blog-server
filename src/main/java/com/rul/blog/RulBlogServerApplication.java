package com.rul.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rul.blog.mapper")
public class RulBlogServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RulBlogServerApplication.class, args);
    }

}

package com.rul.blog.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findUserByEmailTest(){
        System.out.println(userMapper.findUserByEmail("331980169@qq.com"));
    }
}

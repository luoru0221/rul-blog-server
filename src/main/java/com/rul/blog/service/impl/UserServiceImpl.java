package com.rul.blog.service.impl;

import com.rul.blog.mapper.UserMapper;
import com.rul.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


}

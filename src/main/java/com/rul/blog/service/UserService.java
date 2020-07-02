package com.rul.blog.service;

import com.rul.blog.pojo.User;

public interface UserService {

    String sendVerifyCode(String email);

    boolean register(User user, String code);

    User login(User user);
}

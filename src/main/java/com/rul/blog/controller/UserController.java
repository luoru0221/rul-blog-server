package com.rul.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rul.blog.pojo.User;
import com.rul.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 发送邮箱验证码接口
     *
     * @param email 接收验证码的email
     * @return 反馈信息
     */
    @RequestMapping("/sendVerifyCode")
    public Object sendVerifyCode(String email) {
        JSONObject json = new JSONObject();
        String message = userService.sendVerifyCode(email);
        json.put("message", message);
        return json;
    }


    /**
     * 注册
     *
     * @param params 用户填写的注册基本信息 包括user和code
     * @return 注册成功与否
     */
    @RequestMapping("/register")
    public boolean register(@RequestBody Map<String, Object> params) {
        String userJson = JSONObject.toJSONString(params.get("user"));
        User user = JSON.parseObject(userJson,User.class);
        String code = (String) params.get("code");
        return userService.register(user, code);
    }

    /**
     * 登录
     *
     * @param user 用户信息，包含用户名和密码
     * @return 登录成功?登录成功的用户信息:null
     */
    @RequestMapping("/login")
    public User login(User user) {
        return userService.login(user);
    }
}

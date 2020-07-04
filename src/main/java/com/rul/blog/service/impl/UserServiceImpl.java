package com.rul.blog.service.impl;

import com.rul.blog.mapper.UserMapper;
import com.rul.blog.pojo.User;
import com.rul.blog.service.UserService;
import com.rul.blog.util.EmailUtil;
import com.rul.blog.util.Md5Util;
import com.rul.blog.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户业务层实现类
 *
 * @author RuL
 * @time 2020-06-30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private EmailUtil emailUtil;

    /**
     * 发送邮箱验证码
     *
     * @param email 接收方
     * @return "该邮箱已被注册！" OR "发送成功！" OR "发送失败！"
     */
    public String sendVerifyCode(String email) {
        //缓存中已经有当前email的用户信息
        if (redisUtil.hasKey("user:" + email)) {
            return "该邮箱已被注册！";
        }
        //从数据中查询当前email的用户信息
        User user = userMapper.findUserByEmail(email);
        if (user != null) {
            //将用户保存至缓存并设置有效时间为2h
            redisUtil.set("user:" + email, user, 3600 * 2);
            return "该邮箱已被注册！";
        }

        //生成随机验证码
        String verifyCode = emailUtil.randomVerifyCode();
        boolean success = emailUtil.sendEmail(email, verifyCode);
        if (success) {
            //将验证码存入redis并设置有效时间为10分钟
            redisUtil.set("verifyCode:" + email, verifyCode, 600);
            return "发送成功！";
        } else {
            return "发送失败！";
        }
    }

    /**
     * 注册
     *
     * @param user 用户信息
     * @param code 验证码
     * @return 注册成功与否
     */
    @Override
    public boolean register(User user, String code) {
        String verifyCode = (String) redisUtil.get("verifyCode:" + user.getUserEmail());
        if (code.equals(verifyCode)) {
            //对密码进行加密处理
            String mdPassword = Md5Util.md5(user.getUserPassword());
            user.setUserPassword(mdPassword);

            userMapper.insertUser(user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 登录
     *
     * @param user 用户信息，包括邮箱和密码
     * @return 登录成功与否
     */
    @Override
    public User login(@RequestBody User user) {
        //对密码进行加密处理
        String mdPassword = Md5Util.md5(user.getUserPassword());
        user.setUserPassword(mdPassword);

        User userByEmail = userMapper.findUserByEmail(user.getUserEmail());
        if (user.equals(userByEmail)) {
            return userByEmail;
        }
        return null;
    }
}

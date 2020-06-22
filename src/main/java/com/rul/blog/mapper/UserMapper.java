package com.rul.blog.mapper;

import com.rul.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * User持久层接口
 *
 * @author RuL
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户Id查询用户信息
     *
     * @param userId userId
     * @return 用户所有信息
     */
    User findUserById(Integer userId);

    /**
     * 根据用户Email查询用户信息
     *
     * @param userEmail userEmail
     * @return 用户所有信息
     */
    User findUserByEmail(String userEmail);

    /**
     * 新增用户信息
     *
     * @param user 待新增的用户信息（不包括userId）
     * @return 新增用户的userId
     */
    Integer insertUser(User user);

}

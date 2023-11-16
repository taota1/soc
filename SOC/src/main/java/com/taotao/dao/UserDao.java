package com.taotao.dao;

import com.taotao.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author taotao11
 * @version 1.0
 */

@Mapper
@Repository
public interface UserDao{
    List<User> queryUserList();

    User queryUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);

    // 1. 基于注解的方式
    @Select("select * from user")
    List<User> selectAll();



}

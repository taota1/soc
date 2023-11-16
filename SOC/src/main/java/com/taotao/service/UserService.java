package com.taotao.service;

import com.taotao.dao.UserDao;
import com.taotao.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author taotao11
 * @version 1.0
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;


    public User queryUserById(int id) {
        return userDao.queryUserById(id);
    }

    public List<User> queryUserList() {
        return userDao.queryUserList();
    }


    public List<User> getUser() {
        return userDao.selectAll();
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }





}

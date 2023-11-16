package com.taotao.controller;

import com.taotao.service.UserService;

import com.taotao.pojo.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author taotao11
 * @version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserService userService;



    @GetMapping("/health")
    public String start() {
        return "up up ";
    }


    @GetMapping("/queryUserById")
    public User queryUserById(int id){
        User user = userService.queryUserById(id);
        return user;
    }


    @GetMapping("/queryUserList")
    public List<User> queryUserList(){
        List<User> userList = userService.queryUserList();
        for(User user:userList){
            System.out.println(user);
        }
        return userList;
    }

    @GetMapping("/getUserList")
    public List<User> getUserList(){
        return userService.getUser();
    }

//    @ResponseBody
    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "添加成功";
    }

    @GetMapping("/updateUser")
    public String updateUser(){
        User user = new User(3, "taotao", "Aa123456", "男", 18, "18702364123");
        userService.updateUser(user);
        return "更改成功";
    }

    @GetMapping("/updateUserById")
    public User updateUserById(int id){
        User user = new User(id, "taotao", "Aa123456", "男", 18, "18702364123");
        userService.updateUser(user);
        return user;
    }




    @GetMapping("/deleteUser")
    public String deleteUser(int id){
        userService.deleteUser(id);
        return "删除成功";
    }




}


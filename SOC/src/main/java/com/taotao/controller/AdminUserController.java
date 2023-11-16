package com.taotao.controller;

import com.github.pagehelper.PageInfo;
import com.taotao.service.AdminUserService;
import com.taotao.config.Result;
import com.taotao.pojo.AdminUser;
import com.taotao.pojo.Params;
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
@RequestMapping("/admin/user")
public class AdminUserController {


    @Resource
    private AdminUserService adminUserService;


    @GetMapping("/health")
    public String start() {
        return "up up ";
    }


    @GetMapping("/queryUserById")
    public AdminUser queryUserById(int id){
        AdminUser user = adminUserService.queryUserById(id);
        return user;
    }


    @GetMapping("/queryUserList")
    public List<User> queryUserList(){
        List<User> userList = adminUserService.queryUserList();
        for(User user:userList){
            System.out.println(user);
        }
        return userList;
    }

    @GetMapping("/getUserList")
    public Result getUserList(){
        List<User> user = adminUserService.getUser();
        return Result.success(user);
    }

//    @ResponseBody
    @PostMapping("/addOrUpdateUser")
    public Result addUser(@RequestBody AdminUser adminUser){
        if (adminUser.getId()==null) {
            adminUserService.addAdminUser(adminUser);
        } else {
            adminUserService.updateAdminUser(adminUser);
        }
        return Result.success();
    }



    @GetMapping("/deleteUser")
    public Result deleteUser(Integer id){
        adminUserService.deleteUser(id);
        return Result.success();
    }
//
//    @GetMapping("/search")
//    public Result findBySearch(Params params) {
//        List<AdminUser> list = adminUserService.findBySearch(params);
//        return Result.success(list);
//    }

    @GetMapping("/searchPageSize")
    public Result findBySearchPageSize(Params params) {
        PageInfo<AdminUser> info = adminUserService.findBySearchPageSize(params);
        return Result.success(info);
    }


    @PostMapping("/login")
    public Result login(@RequestBody AdminUser adminUser){
        AdminUser loginUser = adminUserService.login(adminUser);
        return Result.success(loginUser);
    }

}


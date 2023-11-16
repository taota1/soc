package com.taotao.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.config.JwtTokenUtils;
import com.taotao.dao.AdminUserDao;
import com.taotao.exception.CustomException;
import com.taotao.pojo.AdminUser;
import com.taotao.pojo.Params;
import com.taotao.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author taotao11
 * @version 1.0
 */
@Service
public class AdminUserService {
    @Resource
    private AdminUserDao adminUserDao;


    public AdminUser queryUserById(int id) {
        return adminUserDao.queryUserById(id);
    }


    public List<User> queryUserList() {
        return adminUserDao.queryUserList();
    }


    public List<User> getUser() {
        return adminUserDao.selectAll();
    }

    public void addAdminUser(AdminUser adminUser) {
        if (adminUser.getName() == null || "".equals(adminUser.getName())) {
            throw new CustomException("用户名不能为空");
        }
        AdminUser user = adminUserDao.queryUserByName(adminUser.getName());
        if (user!=null) {
            throw new CustomException("用户名已存在");
        }
        //设置默认密码
        if (adminUser.getPassword() == null) {
            adminUser.setPassword("123456");
        }
        adminUserDao.addUser(adminUser);
    }

    public void updateAdminUser(AdminUser adminUser) {

        if (adminUser.getName() == null || "".equals(adminUser.getName())) {
            throw new CustomException("用户名不能为空");
        }

        AdminUser user = adminUserDao.queryUsernameByName(adminUser);

        if (user!=null) {
            throw new CustomException("用户名已存在");
        }
        adminUserDao.updateUser(adminUser);
    }

    public void deleteUser(int id) {
        adminUserDao.deleteUser(id);
    }

    public List<AdminUser> findBySearch(Params params) {
        return adminUserDao.findBySearch(params);
    }

    public PageInfo<AdminUser> findBySearchPageSize(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<AdminUser> list = adminUserDao.findBySearchPageSize(params);
        return PageInfo.of(list);
    }

    public AdminUser login(AdminUser adminUser) {

        if (adminUser.getName() == null || "".equals(adminUser.getName())) {
            throw new CustomException("用户名不能为空");
        }
        if (adminUser.getPassword() == null || "".equals(adminUser.getPassword())) {
            throw new CustomException("密码不能为空");
        }
        //用户名 重复性判断
        AdminUser adminUser1 = adminUserDao.findByNameAndPassword(adminUser);

        if (adminUser1 == null) {
            // 如果查出来没有，那说明输入的用户名或者密码有误，提示用户，不允许登录
            throw new CustomException("用户名或密码输入错误");
        }
        //生成token
        String token = JwtTokenUtils.getToken(adminUser1.getId().toString(), adminUser1.getPassword());
        adminUser1.setToken(token);

        return adminUser1;
    }


}

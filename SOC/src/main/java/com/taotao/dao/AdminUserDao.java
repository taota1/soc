package com.taotao.dao;

import com.taotao.pojo.AdminUser;
import com.taotao.pojo.Params;
import com.taotao.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author taotao11
 * @version 1.0
 */

@Mapper
@Repository
public interface AdminUserDao {
    List<User> queryUserList();

    AdminUser queryUserById(int id);

    AdminUser queryUserByName(String name);


    int addUser(AdminUser adminUser);

    int updateUser(AdminUser adminUser);

    int deleteUser(int id);

    // 1. 基于注解的方式
    @Select("select * from user")
    List<User> selectAll();

    List<AdminUser> findBySearch(@Param("params") Params params);

    List<AdminUser> findBySearchPageSize(@Param("params") Params params);

    @Select(" soc.admin_user where name=#{name} limit 1;")
    AdminUser findByName(@Param("name") String name);


    AdminUser queryUsernameByName(AdminUser adminUser);

    AdminUser findByNameAndPassword(AdminUser adminUser);
}

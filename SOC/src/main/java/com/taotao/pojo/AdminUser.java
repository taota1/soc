package com.taotao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


/**
 * @author taotao11
 * @version 1.0
 */

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
//映射数据库的表（name="表名"）
@Table(name = "admin_user")
public class AdminUser {
    //表明id是表的主键
    @Id
    //自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;

    //映射字段
    @Column(name = "name")
    private String name;

    @Column(name = "password")
    public String password;

    @Column(name = "sex")
    public String sex;

    @Column(name = "age")
    public int age;

    @Column(name = "phone")
    public String phone;

    @Transient
    private String token;

}

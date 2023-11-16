package com.taotao.pojo;

import cn.hutool.core.annotation.Alias;
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
@Table(name = "git_project")
public class GitProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Alias("名称")
    @Column(name = "name")
    private String name;

    @Alias("git地址")
    @Column(name = "giturl")
    private String giturl;

    @Alias("项目简介")
    @Column(name = "description")
    private String description;

    @Alias("编程语言")
    @Column(name = "language")
    private String language;

    @Alias("文档地址")
    @Column(name = "readme")
    private String readme;

    @Alias("star")
    @Column(name = "star")
    private String star;

    @Alias("开发者")
    @Column(name = "developer")
    private String developer;

    @Alias("项目类型")
    @Column(name = "type")
    private String type;



}

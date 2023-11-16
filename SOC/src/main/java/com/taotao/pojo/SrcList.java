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
@Table(name = "src_list")
public class SrcList extends AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "site")
    private String site;
    @Column(name = "companyName")
    private String companyName;
    @Column(name = "domain")
    private String domain;
}

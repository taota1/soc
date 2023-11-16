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
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private String price;
    @Column(name = "author")
    private String author;
    @Column(name = "press")
    private String press;
    @Column(name = "img")
    private String img;
    @Column(name = "typeId")
    private Integer typeId;
}

package com.taotao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.taotao.dao")
@SpringBootApplication
public class SocApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocApplication.class, args);
    }

}

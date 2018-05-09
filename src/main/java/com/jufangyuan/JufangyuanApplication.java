package com.jufangyuan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.jufangyuan.daos")
@SpringBootApplication
public class JufangyuanApplication {

    public static void main(String[] args) {
        SpringApplication.run(JufangyuanApplication.class, args);
    }
}

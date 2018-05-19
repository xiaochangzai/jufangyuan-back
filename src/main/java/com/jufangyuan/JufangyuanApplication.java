package com.jufangyuan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
@MapperScan("com.jufangyuan.daos")
@SpringBootApplication
@EnableScheduling
public class JufangyuanApplication extends SpringBootServletInitializer  {

    public static void main(String[] args) {
        SpringApplication.run(JufangyuanApplication.class, args);
    }
    
}

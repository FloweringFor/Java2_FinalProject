package com.example.java2finalproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.java2finalproject.dao")
@EnableScheduling  // 定时启动
public class Java2FinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Java2FinalProjectApplication.class, args);
    }
    
}

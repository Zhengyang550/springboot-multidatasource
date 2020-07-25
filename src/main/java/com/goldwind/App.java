package com.goldwind;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author: zy
 * @Description: 启动代码
 * @Date: 2020-2-2
 */

@SpringBootApplication
public class App {
    public static void main(String[] args){
        //整个程序入口 启动Spring Boot项目
        SpringApplication.run(App.class,args);
    }
}

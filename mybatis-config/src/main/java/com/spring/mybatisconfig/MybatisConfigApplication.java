package com.spring.mybatisconfig;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.spring.mybatisconfig.mapper")
public class MybatisConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisConfigApplication.class, args);
    }

}


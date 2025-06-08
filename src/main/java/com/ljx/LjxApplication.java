package com.ljx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ljx.mapper")
public class LjxApplication {

    public static void main(String[] args) {
        SpringApplication.run(LjxApplication.class, args);
    }
}
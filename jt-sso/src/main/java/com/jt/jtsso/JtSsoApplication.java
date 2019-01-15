package com.jt.jtsso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jt.jtsso.mapper")
public class JtSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JtSsoApplication.class, args);
    }

}


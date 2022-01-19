package com.miaotaizi.jenkins_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.miaotaizi.jenkins_demo.mapper")
public class JenkinsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JenkinsDemoApplication.class, args);
    }

}

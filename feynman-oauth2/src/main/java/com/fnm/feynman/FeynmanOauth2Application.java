package com.fnm.feynman;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 刘彦军
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.fnm.feynman.web.mapper")
public class FeynmanOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(FeynmanOauth2Application.class, args);
    }

}

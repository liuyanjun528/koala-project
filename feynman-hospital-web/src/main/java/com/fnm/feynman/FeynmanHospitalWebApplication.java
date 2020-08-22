package com.fnm.feynman;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 刘彦军
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.fnm.feynman.hospital.web.mapper")
@MapperScan("com.fnm.feynman.web.mapper")
@EnableFeignClients
public class FeynmanHospitalWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeynmanHospitalWebApplication.class, args);
    }

}

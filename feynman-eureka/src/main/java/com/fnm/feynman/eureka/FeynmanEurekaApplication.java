package com.fnm.feynman.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 刘彦军
 */
@SpringBootApplication
@EnableEurekaServer
public class FeynmanEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeynmanEurekaApplication.class, args);
    }

}

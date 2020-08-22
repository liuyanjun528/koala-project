package com.fnm.feynman.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 刘彦军
 */
@SpringBootApplication
@EnableEurekaClient
public class FeynmanGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeynmanGatewayApplication.class, args);
    }

}

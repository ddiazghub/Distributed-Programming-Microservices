package com.uninorte.distributed.programming.loadbalancerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class LoadBalancerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoadBalancerServiceApplication.class, args);
    }
}

package com.uninorte.distributed.programming.postmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PostManagementServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostManagementServiceApplication.class, args);
    }
}

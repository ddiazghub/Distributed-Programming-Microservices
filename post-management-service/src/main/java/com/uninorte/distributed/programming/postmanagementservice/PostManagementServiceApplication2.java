package com.uninorte.distributed.programming.postmanagementservice;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PostManagementServiceApplication2 {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PostManagementServiceApplication2.class);
        
        app.setDefaultProperties(Map.of(
            "server.port", "8011",
            "tcp.server.port", "8021"
        ));
        
        app.run(args);
    }

}

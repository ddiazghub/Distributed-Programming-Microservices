package com.uninorte.distributed.programming.postmanagementservice;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PostManagementServiceApplication1 {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PostManagementServiceApplication1.class);
        
        app.setDefaultProperties(Map.of(
            "server.port", "8010",
            "tcp.server.port", "8020"
        ));
        
        app.run(args);
    }

}

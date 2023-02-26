package com.uninorte.distributed.programming.usermanagementservice;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class UserManagementServiceApplication1 {
    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserManagementServiceApplication1.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8000"));
        app.run(args);
    }
    
}

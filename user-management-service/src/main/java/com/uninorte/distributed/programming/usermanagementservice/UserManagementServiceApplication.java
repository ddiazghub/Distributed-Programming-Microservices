package com.uninorte.distributed.programming.usermanagementservice;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserManagementServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserManagementServiceApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8000"));
        app.run(args);
    }
    
}

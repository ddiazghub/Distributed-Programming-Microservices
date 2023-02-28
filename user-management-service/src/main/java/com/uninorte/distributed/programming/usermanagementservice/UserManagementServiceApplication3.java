package com.uninorte.distributed.programming.usermanagementservice;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserManagementServiceApplication3 {
    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserManagementServiceApplication3.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8002"));
        app.run(args);
    }
    
}

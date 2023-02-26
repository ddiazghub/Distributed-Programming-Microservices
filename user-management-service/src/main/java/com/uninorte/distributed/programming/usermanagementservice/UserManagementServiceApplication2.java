package com.uninorte.distributed.programming.usermanagementservice;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class UserManagementServiceApplication2 {
    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserManagementServiceApplication2.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8001"));
        app.run(args);
    }
    
}

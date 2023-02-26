package com.uninorte.distributed.programming.distributedserviceclient;

import com.uninorte.distributed.programming.distributedserviceclient.model.PostMessage;
import com.uninorte.distributed.programming.distributedserviceclient.model.User;
import com.uninorte.distributed.programming.distributedserviceclient.service.DistributedServiceProxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DistributedServiceClientApplication implements CommandLineRunner {
    
    private Logger log = LoggerFactory.getLogger(DistributedServiceClientApplication.class);
    
    @Autowired
    private DistributedServiceProxy proxy;
    
    public static void main(String[] args) {
        SpringApplication.run(DistributedServiceClientApplication.class, args);
    }
    
    @Override
    public void run(String... args) {
        log.info("Application started");
        ExecutorService exec = Executors.newSingleThreadExecutor();
        
        exec.execute(() -> {
            try {
                Thread.sleep(5000);
                User user = new User(100, "name", "hello123", "name@email.com");
                String token = proxy.createUser(user);
                PostMessage post = new PostMessage(100, "title", "Hello World!!!", 100, null);
                proxy.createPost(token, post);
                log.info("Application finished");
            } catch (InterruptedException ex) {
                log.error("Error", ex);
                Thread.currentThread().interrupt();
            }
        });
    }

}

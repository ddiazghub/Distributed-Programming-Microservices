package com.uninorte.distributed.programming.distributedserviceclient;

import com.uninorte.distributed.programming.distributedserviceclient.model.PostMessage;
import com.uninorte.distributed.programming.distributedserviceclient.model.User;
import com.uninorte.distributed.programming.distributedserviceclient.service.DistributedServiceProxy;
import com.uninorte.distributed.programming.distributedserviceclient.service.ClientContext;
import com.uninorte.distributed.programming.distributedserviceclient.service.TCPService;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableFeignClients
public class DistributedServiceClientApplication implements CommandLineRunner {
    
    private Logger log = LoggerFactory.getLogger(DistributedServiceClientApplication.class);
    private static ConfigurableApplicationContext appCtx;
    
    @Autowired
    private DistributedServiceProxy proxy;
    
    @Autowired
    private ClientContext context;
    
    @Autowired
    private List<TCPService> tcpServices;
    
    public static void main(String[] args) {
        appCtx = SpringApplication.run(DistributedServiceClientApplication.class, args);
    }
    
    @Override
    public void run(String... args) {
        log.info("Application started");
        ExecutorService exec = Executors.newSingleThreadExecutor();
        
        exec.execute(() -> {
            try {
                Thread.sleep(30000);
                User user = new User("name", "hello123", "name@email.com");
                context.init(user);
                user = context.getUser();
                PostMessage post = new PostMessage("title", "Hello World!!!", user.getUser_id());
                proxy.createPost(context.getToken(), post);
                Thread.sleep(5000);
                
                for (TCPService tcp : this.tcpServices)
                    tcp.stop();
                
                log.info("Application finished");
            } catch (Exception ex) {
                log.error("Error", ex);
                appCtx.close();
            }
        });
    }

}

package com.uninorte.distributed.programming.distributedserviceclient;

import com.uninorte.distributed.programming.distributedserviceclient.service.DistributedServiceProxy;
import com.uninorte.distributed.programming.distributedserviceclient.service.ClientContext;
import com.uninorte.distributed.programming.distributedserviceclient.service.TCPService;

import java.awt.EventQueue;
import java.util.List;

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
    public static ConfigurableApplicationContext appCtx;
    
    @Autowired
    private DistributedServiceProxy proxy;
    
    @Autowired
    private ClientContext context;
    
    @Autowired
    private List<TCPService> tcpServices;
    
    private DistributedServiceClientAppView frame;
    
    public static void main(String[] args) {
    	System.setProperty("java.awt.headless", "false");
        appCtx = SpringApplication.run(DistributedServiceClientApplication.class, args);
    }
    
    @Override
    public void run(String... args) {
        log.info("Application started");
    			
        EventQueue.invokeLater(() -> {
            frame = new DistributedServiceClientAppView(proxy, context, tcpServices);
            frame.setVisible(true);
        });
    }

}

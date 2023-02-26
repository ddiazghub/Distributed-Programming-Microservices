/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.distributedserviceclient.configuration;

import com.uninorte.distributed.programming.distributedserviceclient.service.TCPInitializer;
import com.uninorte.distributed.programming.distributedserviceclient.service.TCPService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * @author david
 */
@Configuration
public class TCPConfiguration {
    
    @Value("#{${tcp.sockets.addresses}}")
    private List<String> socketAddresses;
	
    @Bean
    public Bootstrap serverBootstrap(TCPInitializer initializer) {
        Bootstrap b = new Bootstrap();
        
        b.group(workerGroup())
            .channel(NioSocketChannel.class)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .handler(initializer);
        
        return b;
    }

    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup();
    }

    @Bean
    @Scope("singleton")
    public List<TCPService> tcpServices() {
        return new ArrayList<>();
    }
    
    @Bean
    @Scope("singleton")
    public List<InetSocketAddress> tcpSocketAddresses() {
        return this.socketAddresses
            .stream()
            .map(addr -> {
                String[] parts = addr.split(":");
                return new InetSocketAddress(parts[0], Integer.parseInt(parts[1]));
            })
            .collect(Collectors.toList());
    }
    
    @Bean
    public ThreadPoolTaskExecutor exec() {
        ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
        exec.setCorePoolSize(this.socketAddresses.size());
        
        return exec;
    }
                
    @Bean
    public ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener(Bootstrap bootstrap, ThreadPoolTaskExecutor exec) {
        return event -> {
            for (InetSocketAddress address : tcpSocketAddresses()) {
                LoggerFactory.getLogger(TCPConfiguration.class).info(address.toString());
                TCPService tcp = new TCPService(bootstrap, address);
                tcpServices().add(tcp);
                exec.execute(tcp);
            }
        };
    }
    
}

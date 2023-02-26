/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.distributedserviceclient.service;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author david
 */
public class TCPService implements Runnable {
    
    private Bootstrap bootstrap;
    private InetSocketAddress socketAddress;
    private Channel channel;
    private Logger log = LoggerFactory.getLogger(TCPService.class);
    private AtomicBoolean active;
    @Value("${tcp.poll.interval}")
    private int pollInterval;
    
    public TCPService(Bootstrap bootstrap, InetSocketAddress socketAddress) {
        this.bootstrap = bootstrap;
        this.socketAddress = socketAddress;
        this.active = new AtomicBoolean(true);
    }
    
    @Override
    public void run()  {
        while (this.active.get()) {
            try {
                ChannelFuture future = bootstrap.connect(this.socketAddress).sync();
                this.channel = future.channel().closeFuture().sync().channel();
                break;
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                log.error("Connection attempt to host " + this.socketAddress + " failed. Retrying in 10 seconds");
            }
            
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }      
        }
        
        this.stop();
    }
    
    public void stop() {
        this.active.set(false);
        
        if (this.channel != null) {
            this.channel.close();
        }
    }
    
    /*
    public void broadcastNewPost(int user_id) {
        try {
            
            log.info("Pushing notification to all clients");
            TCPNotification notification = new TCPNotification(user_id);
            byte[] json = new ObjectMapper().writeValueAsBytes(notification);
            this.channels.writeAndFlush(Unpooled.copiedBuffer(json));
            log.info("Notification sent");
        } catch (JsonProcessingException ex) {
            log.error("Error", ex);
        }
    }
    */
        
}

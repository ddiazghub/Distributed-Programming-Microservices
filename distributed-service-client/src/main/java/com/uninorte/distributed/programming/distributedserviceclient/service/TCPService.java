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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private int pollInterval;
    
    public TCPService(Bootstrap bootstrap, InetSocketAddress socketAddress, int pollInterval) {
        this.bootstrap = bootstrap;
        this.socketAddress = socketAddress;
        this.active = new AtomicBoolean(true);
        this.pollInterval = pollInterval;
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
                log.error("Connection attempt to host " + this.socketAddress + " failed. Retrying in " + this.pollInterval + " seconds");
            }
            
            try {
                Thread.sleep(this.pollInterval * 1000);
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
}
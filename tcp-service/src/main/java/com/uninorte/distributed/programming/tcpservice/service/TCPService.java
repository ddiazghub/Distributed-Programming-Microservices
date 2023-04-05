/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.tcpservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uninorte.distributed.programming.tcpservice.TCPNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.group.ChannelGroup;
import jakarta.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author david
 */
@Service
public class TCPService {
    
    @Autowired
    private ServerBootstrap serverBootstrap;

    @Autowired
    private InetSocketAddress tcpPort;

    @Autowired
    private ChannelGroup channels;
    
    private Channel serverChannel;
    private final Logger logger = LoggerFactory.getLogger(TCPService.class);

    public void start()  {
        try {
            ChannelFuture serverChannelFuture = serverBootstrap.bind(tcpPort).sync();
            serverChannel = serverChannelFuture.channel().closeFuture().sync().channel();
            logger.info("Started TCP Server at port " + tcpPort.getPort());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @PreDestroy
    public void stop() {
        if ( serverChannel != null ) {
            logger.info("Stopping tcp server");
            serverChannel.close();
            serverChannel.parent().close();
        }
    }
    
    public void broadcast(TCPNotification notification) {
        try {
            String json = new ObjectMapper().writeValueAsString(notification);
            logger.info("Pushing notification to all clients: " + json);
            this.channels.writeAndFlush(Unpooled.copiedBuffer(json, Charset.defaultCharset()));
            logger.info("Notification sent");
        } catch (JsonProcessingException ex) {
            logger.error("Error", ex);
        }
    }
        
}

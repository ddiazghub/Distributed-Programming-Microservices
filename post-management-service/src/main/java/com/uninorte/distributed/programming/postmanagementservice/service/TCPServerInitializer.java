/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.postmanagementservice.service;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author david
 */
@Component
public class TCPServerInitializer extends ChannelInitializer<SocketChannel> {
    
    @Autowired
    private TCPServerHandler handler;
    
    private Logger logger = LoggerFactory.getLogger(TCPService.class);
    
    @Override
    protected void initChannel(SocketChannel channel) {
        channel.pipeline().addLast(this.handler);
        logger.info("New client: " + channel.remoteAddress());
    }
}

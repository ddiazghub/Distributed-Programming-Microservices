/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.distributedserviceclient.service;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author david
 */
@Component
public class TCPInitializer extends ChannelInitializer<SocketChannel> {
    
    @Autowired
    private TCPHandler handler;
    
    @Override
    protected void initChannel(SocketChannel channel) {
        channel.pipeline().addLast(this.handler);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.distributedserviceclient.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uninorte.distributed.programming.distributedserviceclient.model.PostMessage;
import com.uninorte.distributed.programming.distributedserviceclient.model.TCPNotification;
import com.uninorte.distributed.programming.distributedserviceclient.model.UserFile;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author david
 */
@Component
@Sharable
public class TCPHandler extends ChannelInboundHandlerAdapter {
    
    private final Logger log = LoggerFactory.getLogger(TCPHandler.class);
    
    @Autowired
    private DistributedServiceProxy proxy;
    
    @Autowired
    private ClientContext context;
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            ByteBuf buffer = (ByteBuf) msg;
            log.info("Received notification: " + buffer.toString(CharsetUtil.UTF_8));
            TCPNotification notification = new ObjectMapper().readValue(ByteBufUtil.getBytes(buffer), TCPNotification.class);
            String token = context.getToken();
            
            if (token != null) {
            	switch (notification.getNotification_type()) {
                    case "new_post" -> {
                        List<PostMessage> posts = proxy.getPosts(context.getToken(), notification.getUser_id());
                        log.info("Received new post messages: " + Arrays.toString(posts.toArray()));
                        context.getPosts().addAll(posts);
                        context.setPostsChanged(true);
                    }
                    case "new_file" -> {
                        List<UserFile> files = proxy.getFiles(context.getToken(), notification.getUser_id());
                        log.info("Received new files: " + Arrays.toString(files.toArray()));
                        context.getFiles().addAll(files);	                
                        context.setFilesChanged(true);
                    }
            	}
            }
            
            buffer.release();
        } catch (IOException ex) {
            log.error("Error", ex);
        }
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("Connected to server: " + ctx.channel().remoteAddress());
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("Error", cause);
        ctx.close();
    }
    
}

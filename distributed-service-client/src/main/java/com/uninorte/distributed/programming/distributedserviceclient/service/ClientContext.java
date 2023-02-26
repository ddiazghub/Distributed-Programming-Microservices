/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.distributedserviceclient.service;

import com.uninorte.distributed.programming.distributedserviceclient.model.PostMessage;
import com.uninorte.distributed.programming.distributedserviceclient.model.User;
import com.uninorte.distributed.programming.distributedserviceclient.model.UserWithToken;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author david
 */
@Component
public class ClientContext {
    
    private String token;
    private User user;
    private Queue<PostMessage> posts;
    
    private DistributedServiceProxy proxy;
    
    @Autowired
    public ClientContext(DistributedServiceProxy proxy) throws InterruptedException {
        this.proxy = proxy;
    }

    public void init(User user) {
        UserWithToken data = this.proxy.createUser(user);
        this.user = data.getUser();
        this.token = data.getToken();
        this.posts = new ConcurrentLinkedQueue<>(this.proxy.getPosts(this.token, null));
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Queue<PostMessage> getPosts() {
        return posts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}

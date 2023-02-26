/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.distributedserviceclient.service;

import com.uninorte.distributed.programming.distributedserviceclient.model.PostMessage;
import com.uninorte.distributed.programming.distributedserviceclient.model.User;
import java.util.ArrayList;
import java.util.List;
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
    private List<PostMessage> posts;
    
    private DistributedServiceProxy proxy;
    
    @Autowired
    public ClientContext(DistributedServiceProxy proxy) throws InterruptedException {
        this.proxy = proxy;
    }

    public void init() {
        this.user = new User(100, "name", "hello123", "name@email.com");
        this.token = proxy.createUser(user);
        this.posts = proxy.getPosts(token, null);
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<PostMessage> getPosts() {
        return posts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}

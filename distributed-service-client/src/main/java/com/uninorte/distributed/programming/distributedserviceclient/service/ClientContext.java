/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.distributedserviceclient.service;

import com.uninorte.distributed.programming.distributedserviceclient.model.PostMessage;
import com.uninorte.distributed.programming.distributedserviceclient.model.User;
import com.uninorte.distributed.programming.distributedserviceclient.model.UserFile;
import com.uninorte.distributed.programming.distributedserviceclient.model.UserWithToken;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicBoolean;
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
    private ConcurrentSkipListSet<PostMessage> posts;
    private final AtomicBoolean postsChanged;
    private ConcurrentSkipListSet<UserFile> files;
    private final AtomicBoolean filesChanged;
    private DistributedServiceProxy proxy;
    
    @Autowired
    public ClientContext(DistributedServiceProxy proxy){
        this.postsChanged = new AtomicBoolean(false);
        this.filesChanged = new AtomicBoolean(false);
        this.proxy = proxy;
    }

    public void init(User user) {
        UserWithToken data = this.proxy.createUser(user);
        this.user = data.getUser();
        this.token = data.getToken();
        this.posts = new ConcurrentSkipListSet<>(this.proxy.getPosts(this.token, null));
        this.files = new ConcurrentSkipListSet<>(this.proxy.getFiles(this.token, null));
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ConcurrentSkipListSet<PostMessage> getPosts() {
        return posts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getPostsChanged() {
        return postsChanged.get();
    }

    public void setPostsChanged(boolean postsChanged) {
        this.postsChanged.set(postsChanged);
    }

	public ConcurrentSkipListSet<UserFile> getFiles() {
		return files;
	}

	public boolean getFilesChanged() {
		return filesChanged.get();
	}
    
	public void setFilesChanged(boolean filesChanged) {
        this.postsChanged.set(filesChanged);
    }
	
}

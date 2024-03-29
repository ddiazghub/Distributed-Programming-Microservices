/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.postmanagementservice;

import com.uninorte.distributed.programming.postmanagementservice.model.PostMessage;
import com.uninorte.distributed.programming.postmanagementservice.repository.PostRepository;
import com.uninorte.distributed.programming.postmanagementservice.service.AuthorizationService;
import com.uninorte.distributed.programming.postmanagementservice.service.TCPServiceProxy;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author david
 */
@RestController
public class PostController {
    
    @Autowired
    private PostRepository postRepo;
    
    @Autowired
    private AuthorizationService auth;
    
    @Autowired
    private TCPServiceProxy tcp;
    
    private Logger log = LoggerFactory.getLogger(PostController.class);
    
    @GetMapping(path = "/posts")
    public List<PostMessage> get(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @RequestParam(required = false) Integer user_id) {
        auth.authorize(authorization);
        
        return user_id == null ? postRepo.findAll() : postRepo.findByUserId(user_id);
    }
    
    @PostMapping(path = "/posts/create")
    public PostMessage create(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @RequestBody PostMessage post) {
        auth.authorize(authorization);
        post.setPost_creation_timestamp(Timestamp.from(Instant.now()));
        PostMessage p = postRepo.save(post);
        
        try {
            tcp.newPost(post.getUserId());
        } catch (Exception e) {
            log.warn("Could not contact TCP service.");
        }
        
        return p;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.integrationservice.controller;

import com.uninorte.distributed.programming.integrationservice.service.PostManagementServiceProxy;
import com.uninorte.distributed.programming.integrationservice.model.PostMessage;
import java.util.List;
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
public class PostIntegrationController {
    
    @Autowired
    private PostManagementServiceProxy proxy;
    
    @GetMapping(path = "/posts")
    public List<PostMessage> getByUser(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @RequestParam int user_id) {
        return proxy.getByUser(authorization, user_id);
    }
    
    @PostMapping(path = "/posts/create")
    public PostMessage create(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @RequestBody PostMessage post) {
        System.out.println("Autorization: " + authorization);
        return proxy.create(authorization, post);
    }
    
}

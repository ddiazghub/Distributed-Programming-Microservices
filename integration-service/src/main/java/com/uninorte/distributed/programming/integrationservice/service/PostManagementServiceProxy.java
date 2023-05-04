/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uninorte.distributed.programming.integrationservice.service;

import com.uninorte.distributed.programming.integrationservice.model.PostMessage;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author david
 */
@FeignClient(name = "post-management-service", url="http://${POST_MANAGEMENT_SERVICE_SERVICE_HOST:localhost}:8010")
public interface PostManagementServiceProxy {
    
    @GetMapping(path = "/posts")
    public List<PostMessage> get(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @RequestParam(required = false) Integer user_id);
    
    @PostMapping(path = "/posts/create")
    public PostMessage create(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @RequestBody PostMessage post);
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.distributedserviceclient.service;

import com.uninorte.distributed.programming.distributedserviceclient.configuration.FileUploadConfiguration;
import com.uninorte.distributed.programming.distributedserviceclient.model.PostMessage;
import com.uninorte.distributed.programming.distributedserviceclient.model.User;
import com.uninorte.distributed.programming.distributedserviceclient.model.UserWithToken;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author david
 */
@FeignClient(name = "distributed-service-client", url = "${integration.service.url}", configuration = FileUploadConfiguration.class)
public interface DistributedServiceProxy {
    
    @GetMapping(path = "/users")
    public List<User> getAllUsers();
    
    @GetMapping(path = "/users/{user_id}")
    public User getUser(@PathVariable int user_id);
    
    @PostMapping(path = "/users/create")
    public UserWithToken createUser(@RequestBody User user);
    
    @DeleteMapping(path = "/users/delete")
    public List<User> deleteUser(@RequestParam int user_id);
    
    @GetMapping(path = "/posts")
    public List<PostMessage> getPosts(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @RequestParam(required = false) Integer user_id);
    
    @PostMapping(path = "/posts/create")
    public PostMessage createPost(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @RequestBody PostMessage post);
    
    @PostMapping(path = "/files/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @RequestPart(value = "file") MultipartFile file);
    
    @GetMapping(path = "/files/download/{filename}")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @PathVariable String filename);

}

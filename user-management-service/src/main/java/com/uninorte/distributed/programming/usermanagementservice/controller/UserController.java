/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.usermanagementservice.controller;

import com.uninorte.distributed.programming.usermanagementservice.repository.UserRepository;
import com.uninorte.distributed.programming.usermanagementservice.model.User;
import com.uninorte.distributed.programming.usermanagementservice.service.AuthorizationService;
import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author david
 */
@RestController
public class UserController {
    
    @Autowired
    private UserRepository repo;
    
    @Autowired
    private AuthorizationService auth;
    
    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return repo.findAll();
    }
    
    @GetMapping(path = "/users/{user_id}")
    public User getUser(@PathVariable int user_id) {
        return repo.findById(user_id).get();
    }
    
    @PostMapping(path = "/users/create")
    public String createUser(@RequestBody User user) {
        repo.save(user);
        String timestamp = Long.toString(Instant.now().getEpochSecond());
        
        return auth.getToken(user, timestamp);
    }
    
    @DeleteMapping(path = "/users/delete")
    public List<User> deleteUser(@RequestParam int user_id) {
        repo.deleteById(user_id);
        
        return repo.findAll();
    }
    
}

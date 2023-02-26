/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.integrationservice.controller;

import com.uninorte.distributed.programming.integrationservice.service.UserManagementServiceProxy;
import com.uninorte.distributed.programming.integrationservice.model.User;
import com.uninorte.distributed.programming.integrationservice.model.UserWithToken;
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
public class UserIntegrationController {
    
    @Autowired
    private UserManagementServiceProxy proxy;
    
    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return proxy.getAllUsers();
    }
    
    @GetMapping(path = "/users/{user_id}")
    public User getUser(@PathVariable int user_id) {
        return proxy.getUser(user_id);
    }
    
    @PostMapping(path = "/users/create")
    public UserWithToken createUser(@RequestBody User user) {
        return proxy.createUser(user);
    }
    
    @DeleteMapping(path = "/users/delete")
    public List<User> deleteUser(@RequestParam int user_id) {
        return proxy.deleteUser(user_id);
    }
}

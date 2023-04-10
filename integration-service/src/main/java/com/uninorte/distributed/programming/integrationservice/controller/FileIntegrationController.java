/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.integrationservice.controller;

import com.uninorte.distributed.programming.integrationservice.model.UserFile;
import com.uninorte.distributed.programming.integrationservice.service.FileManagementeServiceProxy;
import java.net.InetAddress;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author david
 */
@RestController
public class FileIntegrationController {
    
    @Autowired
    private FileManagementeServiceProxy proxy;
    
    @GetMapping(path = "/files")
    public List<UserFile> getAll(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @RequestParam(required = false) Integer user_id) {
        return proxy.getAll(authorization, user_id);
    }
    
}
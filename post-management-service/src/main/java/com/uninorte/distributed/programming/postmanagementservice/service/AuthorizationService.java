package com.uninorte.distributed.programming.postmanagementservice.service;


import com.uninorte.distributed.programming.postmanagementservice.model.User;
import com.uninorte.distributed.programming.postmanagementservice.repository.UserRepository;
import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author david
 */
@Service
@ComponentScan("com.uninorte.distributed.programming")
public class AuthorizationService {
    
    @Autowired
    private UserRepository userRepo;
    
    public String getToken(User user, String timestamp) {
        String clearText = user.getUser_password() + timestamp;
        String encryptedText = DigestUtils.sha256Hex(clearText);
        String fullText = user.getUser_id() + ";" + timestamp + ";" + encryptedText;
        
        return Base64.encodeBase64URLSafeString(fullText.getBytes());
    }
    
    public User authorize(String authorization) {
        String authDecoded;
        
        try {
            authDecoded = new String(Base64.decodeBase64(authorization), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401));
        }
        
        String[] parts = authDecoded.split(";");
        User user = userRepo.findById(Integer.valueOf(parts[0])).orElseThrow();
        String generatedToken = getToken(user, parts[1]);
        
        if (!generatedToken.equals(authorization))
            throw new ResponseStatusException(HttpStatusCode.valueOf(401));
        
        return user;
    }
    
}

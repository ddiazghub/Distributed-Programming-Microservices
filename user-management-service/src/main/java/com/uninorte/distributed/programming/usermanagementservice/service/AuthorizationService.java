package com.uninorte.distributed.programming.usermanagementservice.service;


import com.uninorte.distributed.programming.usermanagementservice.model.User;
import com.uninorte.distributed.programming.usermanagementservice.repository.UserRepository;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author david
 */
@Service
public class AuthorizationService {
    
    @Autowired
    private UserRepository userRepo;
    
    public String getToken(User user, String timestamp) {
        String clearText = user.getUser_password() + timestamp;
        String encryptedText = DigestUtils.sha256Hex(clearText);
        String fullText = user.getUser_id() + ";" + timestamp + ";" + encryptedText;
        
        return Base64.encodeBase64URLSafeString(fullText.getBytes());
    }
    
}

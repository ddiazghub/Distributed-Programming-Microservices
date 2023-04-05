/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.tcpservice;

import com.uninorte.distributed.programming.tcpservice.service.TCPService;
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
public class TCPNotificationController {
    
    @Autowired
    private TCPService tcp;
    
    @PostMapping(path = "/new_post")
    public void newPost(@RequestBody int user_id) {
        tcp.broadcast(new TCPNotification("new_post", user_id));
    }
    
    @PostMapping(path = "/new_file")
    public void newFile(@RequestBody int user_id) {
        tcp.broadcast(new TCPNotification("new_file", user_id));
    }
    
}

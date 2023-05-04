/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uninorte.distributed.programming.filemanagementservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author david
 */
@FeignClient(name = "tcp-service", url="http://${TCP_SERVICE_SERVICE_HOST:localhost}:8030")
public interface TCPServiceProxy {
    
    @PostMapping(path = "/new_file")
    public void newFile(@RequestBody int user_id);
    
}

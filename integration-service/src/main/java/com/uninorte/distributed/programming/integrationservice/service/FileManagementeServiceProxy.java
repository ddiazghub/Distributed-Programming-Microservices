/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uninorte.distributed.programming.integrationservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import com.uninorte.distributed.programming.integrationservice.model.UserFile;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author david
 */
@FeignClient(name = "file-management-service", url="http://${FILE_MANAGEMENT_SERVICE_SERVICE_HOST:localhost}:8020")
public interface FileManagementeServiceProxy {

    @GetMapping(path = "/files")
    public List<UserFile> getAll(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @RequestParam(required = false) Integer user_id);
    
}

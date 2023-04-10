/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uninorte.distributed.programming.integrationservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;
import com.uninorte.distributed.programming.integrationservice.FileUploadConfiguration;
import com.uninorte.distributed.programming.integrationservice.model.UserFile;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author david
 */
@FeignClient(name = "file-management-service", configuration = FileUploadConfiguration.class)
public interface FileManagementeServiceProxy {

    @GetMapping(path = "/files")
    public List<UserFile> getAll(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @RequestParam(required = false) Integer user_id);
    
}

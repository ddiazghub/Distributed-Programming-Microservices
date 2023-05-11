/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.distributedserviceclient.service;

import com.uninorte.distributed.programming.distributedserviceclient.configuration.FileUploadConfiguration;
import java.net.URI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author david
 */
@FeignClient(name = "file-service-client", url = "http://placeholder-url", configuration = FileUploadConfiguration.class)
public interface FileServiceProxy {
    
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(URI base, @RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @RequestPart(value = "file") MultipartFile file);
    
    @GetMapping(path = "/download/{filename}")
    public byte[] downloadFile(URI base, @RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @PathVariable String filename);

}
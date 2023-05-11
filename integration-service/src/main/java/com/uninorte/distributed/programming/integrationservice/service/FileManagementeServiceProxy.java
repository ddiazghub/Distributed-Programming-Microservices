/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uninorte.distributed.programming.integrationservice.service;

import com.uninorte.distributed.programming.integrationservice.FileUploadConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import com.uninorte.distributed.programming.integrationservice.model.UserFile;
import java.net.URI;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author david
 */
@FeignClient(name = "file-management-service", url="http://${FILE_MANAGEMENT_SERVICE_SERVICE_HOST:localhost}:8020", configuration = FileUploadConfiguration.class)
public interface FileManagementeServiceProxy {

    @GetMapping(path = "/files")
    public List<UserFile> getAll(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @RequestParam(required = false) Integer user_id);

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @RequestPart(value = "file") MultipartFile file);
    
    @GetMapping(path = "/download/{filename}")
    public byte[] downloadFile(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @PathVariable String filename);

}
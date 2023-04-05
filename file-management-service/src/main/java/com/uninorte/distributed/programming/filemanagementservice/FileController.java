/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.filemanagementservice;

import com.uninorte.distributed.programming.filemanagementservice.service.AuthorizationService;
import com.uninorte.distributed.programming.filemanagementservice.service.FileService;
import com.uninorte.distributed.programming.filemanagementservice.service.TCPServiceProxy;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
public class FileController {
    
    @Autowired
    private AuthorizationService auth;
    
    @Autowired
    private TCPServiceProxy tcp;
    
    @Autowired
    private FileService files;
    
    private Logger log = LoggerFactory.getLogger(FileController.class);
    
    @PostMapping(path = "/upload")
    public String upload(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @RequestParam("file") MultipartFile file) throws IOException {
        User user = auth.authorize(authorization);
        files.upload(file);
        
        try {
            tcp.newFile(user.getUser_id());
        } catch (Exception e) {
            log.warn("Could not contact TCP service.");
        }
        
        return "File uploaded successfully";
    }
    
    @GetMapping(path = "/download/{filename}")
    public ResponseEntity<FileSystemResource> download(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization, @PathVariable String filename) throws IOException {
        //auth.authorize(authorization);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        
        FileSystemResource file = files.download(filename);
        
        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.contentLength())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(file);
    }
    
}

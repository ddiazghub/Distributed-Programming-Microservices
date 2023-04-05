/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.distributedserviceclient.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author david
 */
@Service
public class FileService {
    
    private static Path DOWNLOADS = Paths.get(System.getProperty("user.home"));
    
    @Autowired
    private DistributedServiceProxy proxy;
    
    @Autowired
    private ClientContext context;
    
    public void upload(File file) throws IOException {
        String name = file.getName();
        MultipartFile multipart = new MockMultipartFile(name, name, null, new BufferedInputStream(new FileInputStream(file)));
        proxy.uploadFile(context.getToken(), multipart);
    }
    
    public void download(String filename) throws IOException {
        ResponseEntity<InputStreamResource> response = proxy.downloadFile(context.getToken(), filename);
        String[] parts = filename.split("\\.");
        String extension = "." + (parts.length == 1 ? "" : String.join(".", Arrays.copyOfRange(parts, 1, parts.length)));
        Path filepath = DOWNLOADS.resolve(filename);
        int i = 0;
        
        while (Files.exists(filepath)) {
            i++;
            filepath = DOWNLOADS.resolve(parts[0] + i + extension);
        }
        
        Files.copy(response.getBody().getInputStream(), filepath);
    }
    
}

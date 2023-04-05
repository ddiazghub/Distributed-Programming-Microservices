/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.filemanagementservice.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author david
 */
@Service
public class FileService {
    
    private Logger log = LoggerFactory.getLogger(FileService.class);
    private final Path dir;
    
    public FileService(@Value("${file.service.dir}") String dir) throws IOException {
        this.dir = Paths.get(dir);
        
        if (!Files.exists(this.dir))
            Files.createDirectory(this.dir);
    }
    
    public void upload(MultipartFile file) throws IOException {
        log.info("Uploading file: " + file.getOriginalFilename());
        Path filepath = dir.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), filepath);
    }
    
    public FileSystemResource download(String filename) throws IOException {
        return new FileSystemResource(dir.resolve(filename));
    }
}

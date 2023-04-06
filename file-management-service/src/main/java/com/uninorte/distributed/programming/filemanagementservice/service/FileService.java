/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.filemanagementservice.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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
    
    public String upload(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        log.info("Uploading file: " + filename);
        String[] parts = filename.split("\\.");
        String extension = "." + (parts.length == 1 ? "" : String.join(".", Arrays.copyOfRange(parts, 1, parts.length)));
        Path filepath = dir.resolve(filename);
        int i = 0;
        
        while (Files.exists(filepath)) {
            i++;
            filepath = dir.resolve(parts[0] + i + extension);
        }
        
        Files.copy(file.getInputStream(), filepath);
        
        return filepath.getFileName().toString();
    }
    
    public FileSystemResource download(String filename) throws IOException {
        return new FileSystemResource(dir.resolve(filename));
    }
}

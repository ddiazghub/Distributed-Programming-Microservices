/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.distributedserviceclient.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author david
 */
@Service
public class FileService {
    
    private static Path DOWNLOADS = Paths.get(System.getProperty("user.home")).resolve("Downloads");
    
    private List<URI> urls;
    private int current = -1;
    
    @Autowired
    private FileServiceProxy proxy;
    
    @Autowired
    private ClientContext context;
    
    @Autowired
    private FileCipher cipher;
    
    public FileService(@Value("#{${file.service.urls}}") List<String> urls) throws URISyntaxException  {
        this.urls = new ArrayList<>();
        
        for (String url : urls) {
            this.urls.add(new URI(url));
        }
    }
    
    private URI nextUrl() {
        this.current = (this.current + 1) % this.urls.size();
        
        return this.urls.get(current);
    }
    
    public void upload(File file, boolean encrypt) throws IOException {
        String name = file.getName();
        byte[] data = encrypt ? cipher.encrypt(file.toPath(), context.getUser().getUser_password()) : Files.readAllBytes(file.toPath());
        MultipartFile multipart = new MockMultipartFile(name, name, null, data);
        proxy.uploadFile(nextUrl(), context.getToken(), multipart);
    }
    
    public Path download(String filename, String password, boolean decrypt) throws IOException {
        byte[] response = proxy.downloadFile(nextUrl(), context.getToken(), filename);
        String[] parts = filename.split("\\.");
        String extension = "." + (parts.length == 1 ? "" : String.join(".", Arrays.copyOfRange(parts, 1, parts.length)));
        Path filepath = DOWNLOADS.resolve(filename);
        int i = 0;
        
        while (Files.exists(filepath)) {
            i++;
            filepath = DOWNLOADS.resolve(parts[0] + i + extension);
        }
        
        if (decrypt)
            cipher.decrypt(filepath, password, response);
        else
            Files.write(filepath, response);
        
        return filepath;
    }
    
}

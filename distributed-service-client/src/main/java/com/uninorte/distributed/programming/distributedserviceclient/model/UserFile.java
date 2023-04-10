/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.distributedserviceclient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.time.Instant;

/**
 *
 * @author david
 */
public class UserFile {
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer file_id;
    
    @JsonProperty("user_id")
    private Integer userId;
    private String filename;
    private Long size;
    private Timestamp created_at;
    
    public UserFile() {}

    public UserFile(Integer userId, String filename) {
        this.userId = userId;
        this.file_id = null;
        this.filename = filename;
        this.created_at = Timestamp.from(Instant.now());
    }

    public Integer getFile_id() {
        return file_id;
    }

    public void setFile_id(Integer file_id) {
        this.file_id = file_id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

}

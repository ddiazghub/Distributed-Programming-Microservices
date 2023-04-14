/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.distributedserviceclient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

/**
 *
 * @author david
 */
public class UserFile implements Comparable<UserFile> {
    
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

    @Override
    public String toString() {
        return this.filename;
    }
    
    @Override
    public int hashCode() {
        return this.file_id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final UserFile other = (UserFile) obj;
        return Objects.equals(this.file_id, other.file_id);
    }

    @Override
    public int compareTo(UserFile o) {
        // TODO Auto-generated method stub
        return this.file_id.compareTo(o.file_id);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.distributedserviceclient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author david
 */
public class PostMessage implements Comparable<PostMessage> {
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("<HH:mm dd.MM.yyyy>");
    
    @JsonInclude(Include.NON_NULL)
    private Integer post_id;
    
    private String post_content;
    
    @JsonProperty("user_id")
    private int userId;
    
    @JsonInclude(Include.NON_NULL)
    private Timestamp post_creation_timestamp;

    public PostMessage() {}

    public PostMessage(String post_content, int userId) {
        this.post_content = post_content;
        this.userId = userId;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public Timestamp getPost_creation_timestamp() {
        return post_creation_timestamp;
    }

    public void setPost_creation_timestamp(Timestamp post_creation_timestamp) {
        this.post_creation_timestamp = post_creation_timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        String creationDateTime = this.post_creation_timestamp.toLocalDateTime().format(formatter);
        
        return str
            .append(creationDateTime)
            .append("Post [user_id=")
            .append(this.userId)
            .append("]: ")
            .append(this.post_content)
            .toString();
    }
    
    @Override
    public int hashCode() {
        return this.post_id;
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
        
        final PostMessage other = (PostMessage) obj;
        return Objects.equals(this.post_id, other.post_id);
    }

    @Override
    public int compareTo(PostMessage o) {
        // TODO Auto-generated method stub
        return this.post_id.compareTo(o.post_id);
    }
}

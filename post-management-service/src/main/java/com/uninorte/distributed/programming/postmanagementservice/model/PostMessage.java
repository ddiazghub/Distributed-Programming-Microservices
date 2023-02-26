/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.postmanagementservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Timestamp;

/**
 *
 * @author david
 */
@Entity
public class PostMessage {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer post_id;
    
    @Column private String post_title;
    @Column private String post_content;
    
    @Column
    @JsonProperty("user_id")
    private int userId;
    
    @JsonInclude(Include.NON_NULL)
    @Column
    private Timestamp post_creation_timestamp;

    public PostMessage() {}

    public PostMessage(String post_title, String post_content, int userId) {
        this.post_title = post_title;
        this.post_content = post_content;
        this.userId = userId;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
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
    
}
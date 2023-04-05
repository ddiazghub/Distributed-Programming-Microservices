/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.postmanagementservice.repository;

import com.uninorte.distributed.programming.postmanagementservice.model.PostMessage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author david
 */
public interface PostRepository extends JpaRepository<PostMessage, Integer> {
    List<PostMessage> findByUserId(int userId);
}
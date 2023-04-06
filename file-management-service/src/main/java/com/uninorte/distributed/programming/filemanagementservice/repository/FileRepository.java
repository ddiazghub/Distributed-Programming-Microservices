/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.filemanagementservice.repository;

import com.uninorte.distributed.programming.filemanagementservice.model.UserFile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author david
 */
public interface FileRepository extends JpaRepository<UserFile, Integer> {
    List<UserFile> findByUserId(int userId);
}
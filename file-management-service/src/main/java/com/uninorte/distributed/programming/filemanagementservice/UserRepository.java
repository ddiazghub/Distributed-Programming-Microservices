/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.filemanagementservice;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author david
 */
public interface UserRepository extends JpaRepository<User, Integer> {}
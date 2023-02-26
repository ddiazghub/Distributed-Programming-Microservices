/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.shareddatabaseservice.config;

import java.sql.SQLException;
import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author david
 */
@Configuration
public class SharedDatabaseConfiguration {
    
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2DatabaseServer() throws SQLException {
        return Server.createTcpServer(
          "-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
    }
    
}

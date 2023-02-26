/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.distributedserviceclient.model;

/**
 *
 * @author david
 */
public class TCPNotification {
    
    private String notification_type;
    private int user_id;

    public TCPNotification() {
        this.notification_type = "new_post";
    }
    
    public TCPNotification(int user_id) {
        this();
        this.user_id = user_id;
    }

    public String getNotification_type() {
        return notification_type;
    }

    public void setNotification_type(String notification_type) {
        this.notification_type = notification_type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.filemanagementservice.exception;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author david
 */
@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(value = { ResponseStatusException.class, NumberFormatException.class, NoSuchElementException.class })
    protected ResponseEntity<Object> handleUnauthorized(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "401 Unauthorized", new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }
    
    @ExceptionHandler(value = { IOException.class })
    protected ResponseEntity<Object> handleUploadDownloadFail(IOException ex, WebRequest request) {
        if (ex instanceof FileNotFoundException)
            return handleExceptionInternal(ex, "404 File not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
        else return handleExceptionInternal(ex, "500 file upload/download error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    
}

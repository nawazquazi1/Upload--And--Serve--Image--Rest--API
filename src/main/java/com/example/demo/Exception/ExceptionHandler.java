package com.example.demo.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.FileNotFoundException;

/**
 * @author im_na
 */
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception() {
        return ResponseEntity.badRequest().body("Error");
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Object> fileNotFound() {
        return ResponseEntity.badRequest().body("Image Not Found ");
    }

}

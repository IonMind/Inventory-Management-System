package com.zeroplusone.items_inventory_service.exceptionHandlers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Value("${spring.application.name}")
    private String appName;

    @ExceptionHandler(exception = IndexOutOfBoundsException.class)
    public ResponseEntity<?> handleIndexExceptions(IndexOutOfBoundsException ex) {
        return ResponseEntity.badRequest().body(appName+" :IndexOutOfBoundsException => " + ex.getMessage());
    }

    @ExceptionHandler(exception = NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementExceptions(NoSuchElementException ex) {
        return ResponseEntity.badRequest().body(appName+" :NoSuchElementException => " + ex.getMessage());
    }

    @ExceptionHandler(exception = IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentExceptions(IllegalArgumentException ex){
        return ResponseEntity.badRequest().body(appName+" :IllegalArgumentException => " + ex.getMessage());
    }

    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex) {
        System.err.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        ex.printStackTrace();
        System.err.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        return ResponseEntity.badRequest().body(appName+" :Exception => " + ex.getMessage());
    }
}

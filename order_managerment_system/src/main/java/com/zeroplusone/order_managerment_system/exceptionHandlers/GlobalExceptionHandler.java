package com.zeroplusone.order_managerment_system.exceptionHandlers;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = IndexOutOfBoundsException.class)
    public ResponseEntity<?> handleIndexExceptions(IndexOutOfBoundsException ex) {
        return ResponseEntity.badRequest().body("IndexOutOfBoundsException => " + ex.getMessage());
    }

    @ExceptionHandler(exception = NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementExceptions(NoSuchElementException ex) {
        return ResponseEntity.badRequest().body("NoSuchElementException => " + ex.getMessage());
    }

    @ExceptionHandler(exception = IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentExceptions(IllegalArgumentException ex){
        return ResponseEntity.badRequest().body("IllegalArgumentException => " + ex.getMessage());
    }

    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex) {
        // System.err.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        // ex.printStackTrace();
        // System.err.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        return ResponseEntity.badRequest().body("Exception => " + ex.getMessage());
    }
}

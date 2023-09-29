package com.awan.pznspring.mvc;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity argument(MethodArgumentNotValidException err) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error :" + err.getMessage());

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity constraint(ConstraintViolationException err) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
    }
}

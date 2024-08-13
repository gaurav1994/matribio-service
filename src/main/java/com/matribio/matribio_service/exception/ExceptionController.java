package com.matribio.matribio_service.exception;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.matribio.matribio_service.dto.ExceptionEntity;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(RuntimeException.class)
    public  ResponseEntity<ExceptionEntity> createUserBiodataException(RuntimeException ex){
        String randomUUIDString = UUID.randomUUID().toString();
        ExceptionEntity exceptionEntity = new ExceptionEntity(randomUUIDString, ex.getMessage(), null, null);
        return ResponseEntity.status(403).body(exceptionEntity);
    }
}

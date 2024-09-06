package com.matribio.matribio_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.matribio.matribio_service.dto.SimpleMessage;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@CrossOrigin(origins = "*")
public class TestController {

    @GetMapping("/public")
    public SimpleMessage getMethodName() {
        return new SimpleMessage("I am public content! ");
    }

    @GetMapping("/private")
    public SimpleMessage getPrivateMethodName() {
        return new SimpleMessage("I am private content! ");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public SimpleMessage getUserMethodName() {
        return new SimpleMessage("I am user content!");
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public SimpleMessage getAdminMethodName() {
        return new SimpleMessage("I am admin content!");
    }
    
}

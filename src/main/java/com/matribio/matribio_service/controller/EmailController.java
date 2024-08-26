package com.matribio.matribio_service.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matribio.matribio_service.entity.UserBiodata;
import com.matribio.matribio_service.service.EmailService;
import com.matribio.matribio_service.service.UserBiodataService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("email-service")
public class EmailController {
    
    @Autowired EmailService emailService;

    @Autowired UserBiodataService userBiodataService;

    @GetMapping("/sendResumeToEmail/{id}")
    public String sendMailWithAttachment(@PathVariable int id, @RequestParam(value = "email") String email) {
        Optional<UserBiodata> optionUserBiodata = userBiodataService.getSingleUserDtoById(id);
        if (optionUserBiodata.isEmpty()) {
            throw new RuntimeException("User Bio Data not fetched with ID :" + id);
        }
        String status = emailService.sendMailWithAttachment(optionUserBiodata.get(), email);
 
        return status;
    }
}

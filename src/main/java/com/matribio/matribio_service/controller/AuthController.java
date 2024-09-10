package com.matribio.matribio_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.matribio.matribio_service.dto.SimpleMessage;
import com.matribio.matribio_service.dto.UserRequest;
import com.matribio.matribio_service.dto.UserResponse;
import com.matribio.matribio_service.entity.User;
import com.matribio.matribio_service.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponse> userLogin(@RequestBody UserRequest userRequest) {
        User user = User.builder().username(userRequest.getUsername())
                        .password(userRequest.getPassword()).build();
        UserResponse userLoginObject = userService.userLogin(user);
        return ResponseEntity.ok(userLoginObject);
    }

    @PostMapping("/signup")
    public ResponseEntity<SimpleMessage> userSignup(@RequestBody UserRequest userRequest) {

        User user = User.builder()
            .username(userRequest.getUsername())
            .password(userRequest.getPassword())
            .email(userRequest.getEmail())
            .roles("ROLE_USER")
            .source("self_platform")
            .build();

        SimpleMessage userSignupMessage = userService.userSignup(user);
        return ResponseEntity.ok(userSignupMessage);
    }

    @GetMapping("/save-third-party-user")
    public ResponseEntity<SimpleMessage> saveThirdPartyUserInfo(@RequestParam String id_token) {
        SimpleMessage googleUserSignUpMessage = userService.googleUserSignUp(id_token);
        return ResponseEntity.ok(googleUserSignUpMessage);
    }

}

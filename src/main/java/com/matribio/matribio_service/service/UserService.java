package com.matribio.matribio_service.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.matribio.matribio_service.dto.SimpleMessage;
import com.matribio.matribio_service.dto.UserResponse;
import com.matribio.matribio_service.entity.User;

public interface UserService extends UserDetailsService{
    SimpleMessage userSignup(User user);
    SimpleMessage googleUserSignUp(String idToken);
    UserResponse userLogin(User user);
}

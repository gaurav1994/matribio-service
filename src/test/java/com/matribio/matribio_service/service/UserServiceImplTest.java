package com.matribio.matribio_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.matribio.matribio_service.dto.SimpleMessage;
import com.matribio.matribio_service.entity.User;
import com.matribio.matribio_service.repository.UserRepository;

@SpringBootTest
public class UserServiceImplTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService  userService;

    @Test
    void testLoadUserByUsernameForSuccess() {
        User user = User.builder().authProfile("prof")
            .email("xyz@gmail.com").username("xyz").password("xyz")
            .source("source").build();
        Mockito.when(userRepository.findByUsername("xyz")).thenReturn(user);

        UserDetails userDetails = userService.loadUserByUsername("xyz");
        assertEquals(user.getUsername(), userDetails.getUsername());
    }

    @Test
    void testLoadUserByUsernameForFailed() {
        Mockito.when(userRepository.findByUsername("xyz")).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () ->  userService.loadUserByUsername("xyz") );
    }

    @Test
    void testUserSignupForSuccess() {
        User user = User.builder().authProfile("prof").userId("11")
            .email("xyz@gmail.com").username("xyz").password("xyz")
            .source("source").build();
        SimpleMessage message = new SimpleMessage("User Created with UserID : " + user.getUserId());
        Mockito.when(userRepository.save(user)).thenReturn(user);
        SimpleMessage userSignupMessage = userService.userSignup(user);
        assertEquals(message, userSignupMessage);
    }

    @Test
    void testUserSignupForFailed() {
        User user = User.builder().authProfile("prof").userId("11")
            .email("xyz@gmail.com").username("xyz").password("xyz")
            .source("source").build();
        
        Mockito.doThrow(new IllegalArgumentException()).when(userRepository).save(user);
        SimpleMessage userSignupMessage = userService.userSignup(user);
        assertEquals(null, userSignupMessage);
    }

}

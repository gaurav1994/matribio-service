package com.matribio.matribio_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.matribio.matribio_service.dto.SimpleMessage;
import com.matribio.matribio_service.entity.User;
import com.matribio.matribio_service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;
    
    @InjectMocks
    private UserServiceImpl userService;

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
        assertEquals("message", userSignupMessage);
    }
}

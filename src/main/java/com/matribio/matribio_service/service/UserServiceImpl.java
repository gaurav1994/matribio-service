package com.matribio.matribio_service.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.matribio.matribio_service.dto.SimpleMessage;
import com.matribio.matribio_service.dto.UserResponse;
import com.matribio.matribio_service.entity.User;
import com.matribio.matribio_service.repository.UserRepository;
import com.matribio.matribio_service.utils.GoogleTokenValidator;
import com.matribio.matribio_service.utils.JwtUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    GoogleTokenValidator googleTokenValidator;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with these credentials");
        }
        return user;
    }

    @Override
    public SimpleMessage userSignup(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setSource("self_platform");
            User savedUser = userRepository.save(user);
            return new SimpleMessage("User Created with UserID : " + savedUser.getUserId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public SimpleMessage googleUserSignUp(String token) {
        GoogleIdToken validateToken = googleTokenValidator.validateToken(token);
        if (validateToken != null) {
            User userRetrievedFromDb = userRepository.findByUsername(validateToken.getPayload().getEmail());
            if (userRetrievedFromDb == null) {
                User user = new User();
                Payload googleUserPayload = validateToken.getPayload();
                user.setUserId(googleUserPayload.getSubject());
                user.setUsername(googleUserPayload.getEmail());
                user.setEmail(googleUserPayload.getEmail());
                user.setRoles("ROLE_USER");
                user.setAuthProfile(null);
                user.setSource("google_platform");
                User saveUser = userRepository.save(user);
                return new SimpleMessage("User saved with user-id: " + saveUser.getUserId());
            } else {
                throw new RuntimeException("User Already Present in database.");
            }
        } else {
            throw new RuntimeException("Invalid Token provided!");
        }
    }

    @Override
    public UserResponse userLogin(User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), null));
            System.out.println("user authenticated! 🤫");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtils.generateToken(authentication);
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            List<String> roles = authorities.stream().map(avc -> avc.getAuthority()).toList();
            User authenticatedUser = (User) authentication.getPrincipal();
            UserResponse userResponse = new UserResponse(authenticatedUser.getUserId(), authentication.getName(), token,
                    roles, authenticatedUser.getAuthProfile(), authenticatedUser.getSource());
            return userResponse;
        } catch (DisabledException e) {
            e.printStackTrace();
            // throw new UserLockedException("User is Locked");
            throw new RuntimeException("User is Locked");
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            // throw new UserNotFoundException("Invalid Cradentials");
            throw new RuntimeException("Invalid Cradentials");
        }
    }

}

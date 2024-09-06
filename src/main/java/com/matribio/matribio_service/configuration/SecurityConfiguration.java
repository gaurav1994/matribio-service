package com.matribio.matribio_service.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.matribio.matribio_service.filter.AuthenticationFilter;
import com.matribio.matribio_service.service.UserService;
import com.matribio.matribio_service.utils.JwtAuthenticationEntryPoint;


@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    @Autowired
    JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    AuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain webSecutiyFilterChain(HttpSecurity http) throws Exception {

        http
            .cors(cr -> cr.disable())
            .csrf(cs -> cs.disable())
            .authorizeHttpRequests(authorize -> {
                authorize.requestMatchers("/public/**", "/auth/**", "/userbiodata/generatebiodatapdf/**").permitAll();
                authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                authorize.requestMatchers("/user").hasRole("USER");
                authorize.requestMatchers("/admin").hasRole("ADMIN");
                authorize.anyRequest().authenticated();
            }).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint));
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userServiceImpl, PasswordEncoder encoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userServiceImpl);
        authProvider.setPasswordEncoder(encoder);
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationManager authenticationManager(List<AuthenticationProvider> authenticationProviders) {
        return new ProviderManager(authenticationProviders);
    }
}

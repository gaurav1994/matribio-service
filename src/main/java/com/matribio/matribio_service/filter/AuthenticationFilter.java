package com.matribio.matribio_service.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.matribio.matribio_service.entity.User;
import com.matribio.matribio_service.utils.GoogleTokenValidator;
import com.matribio.matribio_service.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private GoogleTokenValidator googleTokenValidator;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain ) throws ServletException, IOException {

        String token = getTokenFromRequest(request); 

        if (token != null) {

            GoogleIdToken validateToken = googleTokenValidator.validateToken(token);
            
            String username="";

            if (validateToken != null) {
                username = validateToken.getPayload().getEmail();
            } else {
                jwtUtils.validateToken(token);
                username = jwtUtils.getUsername(token);
            }

            if (!"".equals(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = new User();
                try {
                    user = (User) userDetailsService.loadUserByUsername(username);
                } catch (UsernameNotFoundException ex) {
                    throw ex;
                } catch(Exception ex ){
                    ex.printStackTrace();
                    throw ex;
                }

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}

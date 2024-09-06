package com.matribio.matribio_service.dto;

import java.util.List;

public class UserResponse {
    private String userId;
    private String username;
    private String accessToken;
    private List<String> roles;
    private String authProfile;
    private String source;

    public UserResponse() {
    }

    public UserResponse(String userId, String username, String accessToken, List<String> roles, String authProfile,
            String source) {
        this.userId = userId;
        this.username = username;
        this.accessToken = accessToken;
        this.roles = roles;
        this.authProfile = authProfile;
        this.source = source;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getAuthProfile() {
        return authProfile;
    }

    public void setAuthProfile(String authProfile) {
        this.authProfile = authProfile;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    
    
}

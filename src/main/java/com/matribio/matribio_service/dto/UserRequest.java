package com.matribio.matribio_service.dto;

public class UserRequest {

    private String email;
    private String username;
    private String password;
    
    public UserRequest() {
    }
    public UserRequest(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public static class UserRequestBuilder {
        private String email;
        private String username;
        private String password;

        public UserRequestBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserRequestBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserRequest build() {
            return new UserRequest(email, username, password);
        }
    }

    public static UserRequestBuilder builder() {
        return new UserRequestBuilder();
    }

}

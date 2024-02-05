package com.ewan.apiplages.response;

public class LoginResponse {
    private String token;

    private long expiresIn;

    // public String getToken() {return token;}

    public LoginResponse(String token, long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }
}

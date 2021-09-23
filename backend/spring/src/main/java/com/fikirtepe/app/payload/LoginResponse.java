package com.fikirtepe.app.payload;

import lombok.Data;

@Data
public class LoginResponse {
    private String username;
    private String accessToken;

    public LoginResponse(String username, String accessToken) {
        this.username = username;
        this.accessToken = accessToken;
    }
}

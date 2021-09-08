package com.fikirtepe.app.payload;

import lombok.Data;

@Data
public class LoginResponse {
    private String userName;
    private String accessToken;

    public LoginResponse(String userName, String accessToken) {
        this.userName = userName;
        this.accessToken = accessToken;
    }
}

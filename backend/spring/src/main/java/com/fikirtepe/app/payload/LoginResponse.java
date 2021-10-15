package com.fikirtepe.app.payload;

import com.fikirtepe.app.model.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Data
public class LoginResponse {
    private String username;
    private String accessToken;
    private Collection<? extends GrantedAuthority> authorities;

    public LoginResponse(String username, String accessToken, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.accessToken = accessToken;
        this.authorities = authorities;
    }
}

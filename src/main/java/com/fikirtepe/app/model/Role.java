package com.fikirtepe.app.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority  {

    ROLE_ADMIN, ROLE_USER;
    //user roles as string "ROLE_ADMIN", "ROLE_USER"
    @Override
    public String getAuthority() {
        return name();
    }
}

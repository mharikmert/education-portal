package com.fikirtepe.app.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority  {

    ROLE_ADMIN, ROLE_STUDENT,ROLE_PARENT,ROLE_TEACHER;

    //returns user roles -> "ROLE_ADMIN"
    @Override
    public String getAuthority() {
        return name();
    }
}

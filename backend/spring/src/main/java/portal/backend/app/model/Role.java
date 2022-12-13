package portal.backend.app.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_ADMIN, ROLE_AUTHORIZED, ROLE_STUDENT, ROLE_TEACHER, ROLE_PARENT, ROLE_GUEST;

    // returns user roles -> "ROLE_ADMIN"
    @Override
    public String getAuthority() {
        return name();
    }
}

package portal.backend.app.payload;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

@Data
public class LoginResponse implements Serializable {
    private String username;
    private String accessToken;
    private Collection<? extends GrantedAuthority> authorities;

    public LoginResponse(String username, String accessToken, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.accessToken = accessToken;
        this.authorities = authorities;
    }
}

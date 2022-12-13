package portal.backend.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PortalBackendApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PortalBackendApplication.class, args);
    }
}

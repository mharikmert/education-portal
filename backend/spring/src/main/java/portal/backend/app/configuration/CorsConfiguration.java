package portal.backend.app.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Value("${allowed.origins}")
    private String[] allowedOrigins;
    @Value("${allowed.methods}")
    private String[] allowedMethods;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigins)
                        .allowedMethods(allowedMethods);

                // detailed cors configuration
                /*
                 * registry.addMapping("/api/v1/auth/**")
                 * .allowedOrigins(allowedOrigins)
                 * .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
                 * 
                 */

            }
        };
    }
}

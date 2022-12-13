package portal.backend.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping(value = "/")
    public String home() {
        return "Portal Backend Application is running successfully..";
    }
}

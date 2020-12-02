package com.fikirtepeinf;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LoginController {

    // HttpServletResponse response
    // response.sendRedirect("template.html");
    @PostMapping("/login")
    public void login(@RequestParam MultiValueMap<String, String> map , HttpServletResponse response) throws IOException {
        System.out.println(map.getFirst("tc"));
        System.out.println(map.getFirst("password"));
        LoginInformation logInfo = new LoginInformation(map.getFirst("tc"), map.getFirst("password"));




    }

}

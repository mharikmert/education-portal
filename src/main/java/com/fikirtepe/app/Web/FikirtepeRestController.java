package com.fikirtepe.app.Web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FikirtepeRestController {
    @RequestMapping("/")
    public String login(){
        return "login";
    }

}

package com.fikirtepe.app.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Configuration
public class LayoutController {
    @RequestMapping("/register")
    public ModelAndView register(){
            return new ModelAndView("register");
    }
    @GetMapping("/KVKK")
    public ModelAndView kvkk(){
        return new ModelAndView("KVKK");
    }
    @RequestMapping("/approval")
    public ModelAndView approval(){
        return new ModelAndView("registrationApproval");
    }
    @RequestMapping("/admin")
    public ModelAndView admin(){
        return new ModelAndView("Admin/adminPanel");
    }
    @RequestMapping("/studentApproval")
    public ModelAndView studentApproval(){
        return new ModelAndView("Admin/studentApproval");
    }

}

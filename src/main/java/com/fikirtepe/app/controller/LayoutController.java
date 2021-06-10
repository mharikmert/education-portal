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
    @RequestMapping("/")
    public ModelAndView main(){
        return new ModelAndView("main");
    }
    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }
    @RequestMapping("/register")
    public ModelAndView register(){
            return new ModelAndView("register");
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

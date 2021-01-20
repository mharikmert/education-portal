package com.fikirtepe.app.Controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Configuration
public class LayoutController {
    @RequestMapping("/")
    public ModelAndView main(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        return mav;
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping("/forgotPassword")
    public ModelAndView forgotPassword(){
            ModelAndView mav = new ModelAndView();
            mav.setViewName("forgot-password-page");
            return mav;
    }

    @RequestMapping("/register")
    public ModelAndView register(){
            ModelAndView mav = new ModelAndView();
//            mav.setViewName("register");
        mav.setViewName("newRegister");
            return mav;
    }

    @RequestMapping("/menu")
    public ModelAndView menu(){
            ModelAndView mav = new ModelAndView();
            mav.setViewName("web_menu");
            return mav;
    }

}

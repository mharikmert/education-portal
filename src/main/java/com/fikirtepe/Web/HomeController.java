package com.fikirtepe.Web;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@Configuration
public class HomeController {
    @RequestMapping("/home")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("currentTime -> ", new Date());
        mav.setViewName("home");
        return mav;
    }
}

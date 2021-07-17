package com.example.boot_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    // get Home page
    @RequestMapping("/home")
    public String getHome(Model model){
        model.addAttribute("title", "Home-BootApp");
        return "home";
    }
    
}

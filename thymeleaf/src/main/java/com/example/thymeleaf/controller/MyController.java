package com.example.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MyController{
    
    @GetMapping("/")
    public String getHome(Model model){

        model.addAttribute("title", "Home Page");

        return "home";
    }

    @GetMapping("/about")
    public String getAbout(Model model){
        model.addAttribute("title", "About Page");
        return "about";
    }
}
package com.example.boot_app.controller;

import com.example.boot_app.data_transfer_object.RegisterUserdto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogInController {

    @RequestMapping("/login")
    public String getHome(Model model){
        
        model.addAttribute("title", "LogIn-BootApp");
        model.addAttribute("user", new RegisterUserdto());
        return "login";
    }
}

package com.example.web_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController{

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getHome(){
        System.out.println("This is inside getHome Url");
        return "home";
    }

}
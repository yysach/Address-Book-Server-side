package com.example.boot_app.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import com.example.boot_app.data_transfer_object.Userdto;
import com.example.boot_app.helper.Message;
import com.example.boot_app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService service;

    // method to run for every /user/** url
    @ModelAttribute
    public void addCommonThings(Model model,Principal principal){
        try{
            model.addAttribute("title", "Dashboard-BootApp");
            Userdto dto = this.service.getUserByUsername(principal.getName());
            model.addAttribute("user", dto);
        }catch(UsernameNotFoundException e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/index")
    public String getDashBoard(Model model,Principal principal){
        model.addAttribute("page", "index");
        return "user/index";
    }
    
    @RequestMapping(value = "/settings")
    public String getSettings(Model model, Principal principal){
        model.addAttribute("page", "settings");
        return "user/settings";
    }

    @RequestMapping(value = "/profile")
    public String getProfile(Model model, Principal principal){
        return "user/profile";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam("oldpassword") String oldpassword, @RequestParam("newpassword") String newpassword,Principal principal,HttpSession session){
        try{
            if(this.service.resetPassword(oldpassword,newpassword,principal.getName())){
                session.setAttribute("message", new Message("password changed successfully","alert-success"));
                return "redirect:/user/index";
            }
            else{
                session.setAttribute("message", new Message("Invalid Old Password !!","alert-danger"));
                return "redirect:/user/settings";

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        session.setAttribute("message", new Message("couldn't change password. reset again !!","alert-warning"));
        return "redirect:/user/settings";

    }
    
}

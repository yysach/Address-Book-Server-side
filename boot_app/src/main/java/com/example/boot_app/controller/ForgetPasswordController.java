package com.example.boot_app.controller;
import javax.servlet.http.HttpSession;
import com.example.boot_app.helper.Message;
import com.example.boot_app.service.UserService;
import com.example.boot_app.service.Impl.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ForgetPasswordController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService Service;

    // to add title to every page in process
    @ModelAttribute
    public void addCommonTitle(Model model){
        model.addAttribute("title", "ForgetPassword-BootApp");
    }


    @GetMapping(value = "/forget_password")
    public String getForgetPasswordForm(Model model){
        return "forget-password";
    }

    @PostMapping(value = "/send")
    public String sendOTP(@RequestParam("email") String email,HttpSession session){
        try{
            String otp = this.emailService.composeMail("yysachin11@gmail.com", email, "g.m.a.i.l.com");
            session.setAttribute("otp",otp);
            session.setAttribute("email", email);
            session.setAttribute("message", new Message("OTP has been mailed.","alert-success"));
            return "verify-otp";
        }catch(Exception e){
            e.printStackTrace();
            session.setAttribute("message", new Message("Something went wrong !!","alert-warning"));
        }
        return "redirect:/login";        
    }
    @PostMapping(value = "/check")
    public String checkOTP(@RequestParam("otp") String otp,HttpSession session){

        String sessionOtp = (String) session.getAttribute("otp");
        if(!sessionOtp.equals(otp)){
            session.setAttribute("message", new Message("Invalid OTP !!","alert-danger"));
            return "verify-otp";
        }else{
            return "redirect:/take_password";
        }
    }

    @GetMapping(value = "/take_password")
    public String getpassword(){
        return "take-password";
    }

    @PostMapping(value = "/new_password")
    public String newPassword(@RequestParam("password") String password,HttpSession session){
        try{
            String email = (String)session.getAttribute("email");
            this.Service.changePassword(email,password);
            session.setAttribute("message", new Message("Password changed successfully !!","alert-success"));
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/login";
    }
}

package com.example.boot_app.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.example.boot_app.data_transfer_object.RegisterUserdto;
import com.example.boot_app.data_transfer_object.Userdto;
import com.example.boot_app.helper.Message;
import com.example.boot_app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    // get signUp form
    @RequestMapping("/signup")
    public String getSignUp(Model model){
        model.addAttribute("title", "SignUp-BootApp");
        model.addAttribute("registerUser", new RegisterUserdto());
        return "signUp";
    }

    // submit signup form
    @RequestMapping(value = "/saveUser",method = RequestMethod.POST)
    public String createUser(@Valid @ModelAttribute("registerUser") RegisterUserdto registerUserdto,BindingResult result,
    @RequestParam(value = "checkbox",defaultValue ="false") boolean agreement,Model model,HttpSession session) throws SQLException{

        if(!agreement){
            session.setAttribute("message", new Message("You need to check the box !!","alert-danger"));
            return "signUp";
        }
        
        if(result.hasErrors()){
            if(result.hasFieldErrors("username")){
                session.setAttribute("message", new Message(result.getFieldError("username").getDefaultMessage(),"alert-danger"));
            }else if(result.hasFieldErrors("email")){
                session.setAttribute("message", new Message(result.getFieldError("email").getDefaultMessage(),"alert-danger"));
            }else if(result.hasFieldErrors("password")){
                session.setAttribute("message", new Message(result.getFieldError("password").getDefaultMessage(),"alert-danger"));
            }else if(result.hasFieldErrors("confirmPassword")){
                session.setAttribute("message", new Message(result.getFieldError("confirmPassword").getDefaultMessage(),"alert-danger"));
            }
            return "signUp";
        }


        if(!registerUserdto.getPassword().equals(registerUserdto.getConfirmPassword())){
            session.setAttribute("message",  new Message ("Password doesn't matched !!","alert-danger"));
            return "signUp";
        }

        try{
            Userdto dto = this.userService.RegisterUser(registerUserdto);
            session.setAttribute("message", new Message("You are Registered Successfully.","alert-success"));
            model.addAttribute("user", dto);
            return "redirect:login";
        }
        catch(DataIntegrityViolationException e){
            e.printStackTrace();
            session.setAttribute("message", new Message("UserName or Email already exist","alert-danger"));
            model.addAttribute("registerUser", registerUserdto);
            return "signup";
        }catch(Exception e){
            e.printStackTrace();
            session.setAttribute("message", new Message("Something Went wrong","alert-danger"));
            model.addAttribute("registerUser", registerUserdto);
            return "signup";
        }
    }


    // check integrity that username or email should not exist already
    @PostMapping("/integrity")
    @ResponseBody
    public String checkIntegrity(@RequestParam("username") String username,@RequestParam("email") String email){

        try{
            boolean flag = this.userService.userIntegrity(username,email);
            if(flag==true){
                return "not_taken";
            }else{
                return "taken";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "not_taken";
    }
}

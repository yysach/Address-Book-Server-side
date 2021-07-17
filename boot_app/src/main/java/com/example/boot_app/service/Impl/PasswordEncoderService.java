package com.example.boot_app.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    public String encodePass(String pass){
        return encoder.encode(pass);
    }

    public boolean crossMatchPassword(String oldpassword,String password){
        if(encoder.matches(oldpassword, password)){
            return true;
        }
        return false;
    }
        
}

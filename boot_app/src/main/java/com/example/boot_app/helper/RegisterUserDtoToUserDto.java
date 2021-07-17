package com.example.boot_app.helper;

import com.example.boot_app.data_transfer_object.RegisterUserdto;
import com.example.boot_app.data_transfer_object.Userdto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class RegisterUserDtoToUserDto {

    @Autowired
    private Userdto dto;

    @Bean
    public Userdto convertRegisterDtoToUserDto(RegisterUserdto registerUserdto){
        
        dto.setEmail(registerUserdto.getEmail());
        dto.setUsername(registerUserdto.getUsername());
        return dto;
    }
    
}

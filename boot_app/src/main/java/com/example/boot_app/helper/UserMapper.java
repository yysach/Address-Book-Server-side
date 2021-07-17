package com.example.boot_app.helper;

import com.example.boot_app.Entity.User;
import com.example.boot_app.data_transfer_object.Userdto;

import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public Userdto mapToUserDto(User user){
        Userdto userdto = new Userdto();
        userdto.setUsername(user.getUsername());
        userdto.setEmail(user.getEmail());
        return userdto;
    }
    
}

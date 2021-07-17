package com.example.quiz_app.helper;
import com.example.quiz_app.dto.UserDto;
import com.example.quiz_app.model.User;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto mapToUserDto(User user){

        UserDto dto = new UserDto();

        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }
    
}

package com.example.quiz_app.services.Impl;

import com.example.quiz_app.dto.RegisterDto;
import com.example.quiz_app.dto.UserDto;
import com.example.quiz_app.helper.UserMapper;
import com.example.quiz_app.model.User;
import com.example.quiz_app.repository.UserRepository;
import com.example.quiz_app.services.RegisterUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder encoder;



    @Override
    public UserDto getRegisterUser(RegisterDto registerDto) throws DataIntegrityViolationException{
        if(!checkPassword(registerDto.getPassword(), registerDto.getCpassword())){
            return null;
        }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(encoder.encode(registerDto.getPassword()));
        user.setRole("ROLE_USER");

        return this.userMapper.mapToUserDto(this.userRepository.save(user));
    }

    @Override
    public boolean checkPassword(String password, String cpassword) {

        if(password.equals(cpassword)){
            return true;
        }

        return false;
    }
    
}

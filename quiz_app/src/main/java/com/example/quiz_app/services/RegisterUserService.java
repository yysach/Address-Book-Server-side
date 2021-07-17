package com.example.quiz_app.services;

import com.example.quiz_app.dto.RegisterDto;
import com.example.quiz_app.dto.UserDto;

import org.springframework.dao.DataIntegrityViolationException;

public interface RegisterUserService {
    UserDto getRegisterUser(RegisterDto registerDto) throws DataIntegrityViolationException;
    boolean checkPassword(String password, String cpassword);
}

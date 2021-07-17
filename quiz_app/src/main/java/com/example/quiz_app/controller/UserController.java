package com.example.quiz_app.controller;

import com.example.quiz_app.helper.Message;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController {

    @GetMapping("/index")
    public ResponseEntity <Message > getDashboard(){
        return ResponseEntity.status(HttpStatus.valueOf(200)).body(new Message("This is dashboard","success"));
    }
    @GetMapping("/profile")
    public ResponseEntity <Message > getProfile(){
        return ResponseEntity.status(HttpStatus.valueOf(200)).body(new Message("This is profile","success"));
    }
}

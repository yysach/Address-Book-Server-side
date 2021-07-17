package com.example.quiz_app.controller;
import javax.validation.Valid;
import com.example.quiz_app.dto.RegisterDto;
import com.example.quiz_app.dto.UserDto;
import com.example.quiz_app.helper.Message;
import com.example.quiz_app.services.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private RegisterUserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<Message> getRegister(@Valid @RequestBody RegisterDto registerDto,BindingResult result){

        System.out.println(registerDto);
        if(result.hasErrors()){
            System.out.println(result.getAllErrors());
            if(result.hasFieldErrors("username")){
                return ResponseEntity.status(HttpStatus.valueOf(406)).body(new Message(result.getFieldError("username").getDefaultMessage(),"error"));
            }else if(result.hasFieldErrors("email")){
                return ResponseEntity.status(HttpStatus.valueOf(406)).body(new Message(result.getFieldError("email").getDefaultMessage(),"error"));
            }else if(result.hasFieldErrors("password")){
                return ResponseEntity.status(HttpStatus.valueOf(406)).body(new Message(result.getFieldError("password").getDefaultMessage(),"error"));
            }else if(result.hasFieldErrors("cpassword")){
                return ResponseEntity.status(HttpStatus.valueOf(406)).body(new Message(result.getFieldError("cpassword").getDefaultMessage(),"error"));
            }
        }

        try{
            UserDto dto = this.userService.getRegisterUser(registerDto);
            if(dto==null){
                return ResponseEntity.status(HttpStatus.valueOf(401)).body(new Message("Password doesn't matched","error"));
            }
            return ResponseEntity.status(HttpStatus.valueOf(201)).body(new Message("User Registered Successfully","success"));
        }catch(DataIntegrityViolationException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.valueOf(406)).body(new Message("username or email exist already","error"));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.valueOf(500)).body(new Message("something went wrong.....","error"));
        }
    }    
}

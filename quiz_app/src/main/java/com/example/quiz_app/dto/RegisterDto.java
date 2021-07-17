package com.example.quiz_app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterDto {

    @NotBlank(message = "username can not null !!")
    @Size(min=4,message = "username should be at least 4 char long !!")
    private String username;

    @NotBlank(message = "email can not null !!")
    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "email is not valid !!")
    private String email;

    @Size(min=4,message = "password should be at least 4 char long !!")
    private String password;

    @Size(min=4,message = "confirm password should be at least 4 char long !!")
    private String cpassword;



    public RegisterDto() {
        super();
    }
    public RegisterDto(String username, String email, String password, String cpassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.cpassword = cpassword;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getCpassword() {
        return cpassword;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }
    @Override
    public String toString() {
        return "RegisterDto [cpassword=" + cpassword + ", email=" + email + ", password=" + password + ", username="
                + username + "]";
    }

    

    
}

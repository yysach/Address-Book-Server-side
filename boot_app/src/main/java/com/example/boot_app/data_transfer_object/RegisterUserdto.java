package com.example.boot_app.data_transfer_object;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterUserdto {

    @NotBlank(message = "Username can not be blank !!")
    @Size(min = 4,max=8,message = "Username must be 4-8 character long !!")
    private String username;

    @NotBlank(message = "Email can not be blank !!")
    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Email Address is invalid !!")
    private String email;

    @Size(min = 5,message = "Password must be atleast 5 character long !!")
    private String password;

    @Size(min = 5,message = "Confirm Password must be atleast 5 character long !!")
    private String confirmPassword;


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
}

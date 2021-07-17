package com.example.boot_app.service;
import com.example.boot_app.data_transfer_object.RegisterUserdto;
import com.example.boot_app.data_transfer_object.Userdto;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    Userdto RegisterUser(RegisterUserdto registerUserdto) throws DataIntegrityViolationException;
    Userdto getUserByUsername(String username) throws UsernameNotFoundException;
    void changePassword(String email,String password) throws UsernameNotFoundException;
    boolean userIntegrity(String username,String email) throws UsernameNotFoundException;
    boolean resetPassword(String oldpassword,String newpassword,String username) throws UsernameNotFoundException;
}

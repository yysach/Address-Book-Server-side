package com.example.boot_app.service.Impl;

import com.example.boot_app.Entity.User;
import com.example.boot_app.Repository.UserRepository;
import com.example.boot_app.helper.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("Username Not found");
        }
        
        return new UserPrincipal(user);
    }


}
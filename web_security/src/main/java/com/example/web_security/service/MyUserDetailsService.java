package com.example.web_security.service;

import com.example.web_security.Entity.User;
import com.example.web_security.dao.UserRepository;

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
            throw new UsernameNotFoundException("error 404");
        }
        return new UserPrincipal(user);
    }


}
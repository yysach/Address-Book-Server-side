package com.example.web_security.dao;

import com.example.web_security.Entity.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository< User , Integer> {

    User findByUsername(String username);
    
}

package com.example.quiz_app.services;

import java.util.List;

import com.example.quiz_app.dto.ContactDto;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ContactService {

    List<ContactDto> getAllContacts(String username) throws UsernameNotFoundException;
    ContactDto saveContact(ContactDto contactDto,String username) throws UsernameNotFoundException;
    
}

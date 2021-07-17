package com.example.quiz_app.controller;
import com.example.quiz_app.helper.Message;
import com.example.quiz_app.services.ContactService;

import java.security.Principal;
import java.util.List;

import com.example.quiz_app.dto.ContactDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class ContactController {


    @Autowired
    private ContactService contactService;

    @PostMapping("/add-contact")
    public ResponseEntity <Message > addContact(@RequestBody ContactDto contactDto,Principal principal){
        System.out.println(contactDto);
        System.out.println(principal.getName());
        try{
            ContactDto dto = contactService.saveContact(contactDto, principal.getName());
        }catch(UsernameNotFoundException e){
            System.out.println(e);

        }catch(BadCredentialsException e){
            System.out.println(e);
        }
    
        return ResponseEntity.status(HttpStatus.valueOf(200)).body(new Message("contact added !","success"));
    }
    @GetMapping("/show-contacts")
    public  List<ContactDto> showContacts(Principal principal){

        System.out.println(principal.getName());
        List<ContactDto> contacts=null;
        try{
            contacts = this.contactService.getAllContacts(principal.getName());
            
        }catch(UsernameNotFoundException e){
            System.out.println(e);

        }catch(BadCredentialsException e){
            System.out.println(e);
        }
        return contacts;
    }
    
}

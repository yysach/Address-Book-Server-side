package com.example.quiz_app.helper;

import com.example.quiz_app.dto.ContactDto;
import com.example.quiz_app.model.Contact;

import org.springframework.stereotype.Component;


@Component
public class ContactMapper{

    public ContactDto convertToDto(Contact contact){

        ContactDto contactDto = new ContactDto();
        contactDto.setName(contact.getName());
        contactDto.setEmail(contact.getEmail());
        contactDto.setPhone(contact.getPhone());
        contactDto.setAbout(contact.getAbout());
        return contactDto;
    }
    public Contact convertFromDto(ContactDto contactDto){

        Contact contact = new Contact();
        contact.setName(contactDto.getName());
        contact.setEmail(contactDto.getEmail());
        contact.setPhone(contactDto.getPhone());
        contact.setAbout(contactDto.getAbout());
        return contact;
    }
}
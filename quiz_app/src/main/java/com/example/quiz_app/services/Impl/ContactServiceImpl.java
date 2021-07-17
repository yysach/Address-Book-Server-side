package com.example.quiz_app.services.Impl;

import java.util.ArrayList;
import java.util.List;

import com.example.quiz_app.dto.ContactDto;
import com.example.quiz_app.helper.ContactMapper;
import com.example.quiz_app.model.Contact;
import com.example.quiz_app.model.User;
import com.example.quiz_app.repository.ContactRepository;
import com.example.quiz_app.repository.UserRepository;
import com.example.quiz_app.services.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public List<ContactDto> getAllContacts(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username);
        List<Contact> contacts = contactRepository.findByUserId(user.getId());

        List<ContactDto> contactList = new ArrayList<>();
        for(Contact c : contacts){
            contactList.add(contactMapper.convertToDto(c));
        }
        return contactList;

    }

    @Override
    public ContactDto saveContact(ContactDto contactDto,String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        Contact contact = contactMapper.convertFromDto(contactDto);
        contact.setUser(user);
        return contactMapper.convertToDto(contactRepository.save(contact));
    }
    
}

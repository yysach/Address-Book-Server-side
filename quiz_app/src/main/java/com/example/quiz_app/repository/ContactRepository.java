package com.example.quiz_app.repository;
import java.util.List;

import com.example.quiz_app.model.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer>{
    @Query("SELECT c FROM Contact c WHERE c.user.id = ?1 ")
    List<Contact> findByUserId(Integer user_id);
    
}

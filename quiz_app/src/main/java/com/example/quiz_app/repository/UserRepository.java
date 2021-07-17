package com.example.quiz_app.repository;

import com.example.quiz_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    User findByUsername(String username);
}

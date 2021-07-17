package com.example.boot_app.Repository;

import java.util.List;

import com.example.boot_app.Entity.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository  extends JpaRepository< Task, Integer> {
    
    @Query("SELECT t FROM Task t where t.user.id = :user_id")
    List<Task> findByUserId(@Param("user_id") int user_id);
    
}

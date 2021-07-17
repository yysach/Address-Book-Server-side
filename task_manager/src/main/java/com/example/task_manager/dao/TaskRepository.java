package com.example.task_manager.dao;

import com.example.task_manager.Entity.Task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository  extends JpaRepository<Task, Integer>{
    
}

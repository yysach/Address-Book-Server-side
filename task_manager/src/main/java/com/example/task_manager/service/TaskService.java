package com.example.task_manager.service;

import java.util.List;

import com.example.task_manager.Entity.Task;
import com.example.task_manager.dao.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks(){
        List<Task>  list = this.taskRepository.findAll();
        return list;
    }

    public Task saveTask(Task task){
        return this.taskRepository.save(task);
    }


    public void deleteTAsk(int id){
        this.taskRepository.deleteById(id);;
    }

    
}

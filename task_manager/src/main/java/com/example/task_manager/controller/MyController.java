
package com.example.task_manager.controller;

import java.util.List;

import com.example.task_manager.Entity.Task;
import com.example.task_manager.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class MyController{

    @Autowired
    private TaskService service;

    @GetMapping("/tasks")
    public List<Task> getAllTask(){
        List<Task> list = this.service.getAllTasks();
        return list;
    }

    @PostMapping("/task")
    public Task postTask(@RequestBody Task task){
        System.out.println(task);
        return this.service.saveTask(task);
    }
    @DeleteMapping("/task/{id}")
    public void deleleTask(@PathVariable("id") int id){
        this.service.deleteTAsk(id);
    }

}
package com.example.boot_app.service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.example.boot_app.Entity.Task;
import com.example.boot_app.Entity.User;
import com.example.boot_app.Repository.TaskRepository;
import com.example.boot_app.Repository.UserRepository;
import com.example.boot_app.data_transfer_object.TaskDto;
import com.example.boot_app.helper.TaskMapper;
import com.example.boot_app.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;
    

    @Override
    public TaskDto saveTask(String username,TaskDto taskDto) throws UsernameNotFoundException {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setUser(this.userRepository.findByUsername(username));
        return taskMapper.convertTaskToDto(this.taskRepository.save(task));
    }


    @Override
    public List<TaskDto> getAllTask(String username) throws UsernameNotFoundException {

        int user_id = this.userRepository.findByUsername(username).getId();
        List<Task> taskList = this.taskRepository.findByUserId(user_id);

        List<TaskDto> list = new ArrayList<>();
        for(Task t: taskList){
            list.add(taskMapper.convertTaskToDto(t));
        }
        return list;
    
    }


    @Override
    public boolean deleteTask(String username,int taskId) throws UsernameNotFoundException {

        User user = this.userRepository.findByUsername(username);
        Task task = this.taskRepository.findById(taskId).get();
        if(user.getId()!=task.getUser().getId()){
            return false;
        }else{
            this.taskRepository.deleteById(taskId);
            return true;

        }
    }
    
}

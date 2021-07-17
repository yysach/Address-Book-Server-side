package com.example.boot_app.helper;

import com.example.boot_app.Entity.Task;
import com.example.boot_app.data_transfer_object.TaskDto;

import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDto convertTaskToDto(Task task){
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        return dto;
    }
    
}

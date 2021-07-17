package com.example.boot_app.service;
import java.util.List;
import com.example.boot_app.data_transfer_object.TaskDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface TaskService {
    TaskDto saveTask(String username,TaskDto taskDto) throws UsernameNotFoundException;
    List<TaskDto> getAllTask(String username) throws UsernameNotFoundException;
    boolean deleteTask(String username,int taskId) throws UsernameNotFoundException;
}

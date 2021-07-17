package com.example.boot_app.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.boot_app.data_transfer_object.TaskDto;
import com.example.boot_app.data_transfer_object.Userdto;
import com.example.boot_app.helper.Message;
import com.example.boot_app.service.TaskService;
import com.example.boot_app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/user")
public class TaskController {


    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    // method to run for every /user/** url
    @ModelAttribute
    public void addCommonThings(Model model,Principal principal){
        try{
            model.addAttribute("title", "Dashboard-BootApp");
            Userdto dto = this.userService.getUserByUsername(principal.getName());
            model.addAttribute("user", dto);
        }catch(UsernameNotFoundException e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/add_task")
    public String getAddTask(Model model, Principal principal){
        model.addAttribute("task", new TaskDto());
        model.addAttribute("page", "addTask");
        return "user/add-task";
    }
    
    @RequestMapping(value = "/show_tasks")
    public String getShowTasks(Model model, Principal principal){
        model.addAttribute("page", "showTask");
        try{
            List<TaskDto> taskList = this.taskService.getAllTask(principal.getName());
            model.addAttribute("tasks", taskList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "user/show-tasks";
    }

    @PostMapping(value = "/process_task")
    public String processTask(@ModelAttribute("task") TaskDto taskDto,Model model, Principal principal,HttpSession session){
        try{
            TaskDto dto = this.taskService.saveTask(principal.getName(), taskDto);
            dto.setDescription("");
            session.setAttribute("message", new Message("Task added. Add more !!","alert-success"));
            model.addAttribute("task", dto);
        }catch(Exception e){
            e.printStackTrace();
            session.setAttribute("message", new Message("Something went wrong. Add again !!","alert-danger"));
            model.addAttribute("task", taskDto);
            return "user/add-task";
        }
        return "redirect:/user/add_task";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteTask(@PathVariable("id") int id,Principal principal,Model model,HttpSession session){

        System.out.println(id);
        try{
            if(this.taskService.deleteTask(principal.getName(),id)){
                session.setAttribute("message", new Message("task deleted successfully !!","alert-success"));
            }else{
                session.setAttribute("message", new Message("Sorry You are not allowed to delete that task","alert-warning"));
            }
        }catch(Exception e){
            e.printStackTrace();
            session.setAttribute("message", new Message("Sorry, Something went wrong","alert-danger"));
            return "redirect:/user/show_tasks";
        }
        return "redirect:/user/show_tasks";
    }
    
}

package com.example.task_management.service.impl;

import com.example.task_management.dto.Task.TaskRequest;
import com.example.task_management.dto.Task.TaskResponse;
import com.example.task_management.entity.Role;
import com.example.task_management.entity.Status;
import com.example.task_management.entity.Task;
import com.example.task_management.entity.User;
import com.example.task_management.exception.ResourceNotFoundException;
import com.example.task_management.exception.UnauthorizedException;
import com.example.task_management.repository.TaskRepository;
import com.example.task_management.repository.UserRepository;
import com.example.task_management.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private UserRepository userRepo;


    private User getCurrentUser() {
        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }


    public TaskResponse createTask(TaskRequest taskRequest) {

        User user = getCurrentUser();

        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());

        task.setStatus(taskRequest.getStatus() != null
                ? taskRequest.getStatus()
                : Status.PENDING);

        task.setCreatedBy(user);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        Task savedTask = taskRepo.save(task);

        return mapToResponse(savedTask);
    }


    public List<TaskResponse> getTasks() {

        User user = getCurrentUser();
        List<Task> tasks;

        if (user.getRole() == Role.ADMIN) {
            tasks = taskRepo.findAll();
        } else {
            tasks = taskRepo.findByCreatedBy(user);
        }

        return tasks.stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {

        User user = getCurrentUser();

        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        // 🔐 Authorization check
        if (user.getRole() == Role.USER &&
                !task.getCreatedBy().getId().equals(user.getId())) {

            throw new UnauthorizedException("You are not allowed to update this task");
        }

        // 🔄 Update fields
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());
        task.setUpdatedAt(LocalDateTime.now());

        Task savedTask = taskRepo.save(task);

        return mapToResponse(savedTask);
    }


    public void deleteTask(Long id) {

        User user = getCurrentUser();

        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        // 🔐 Authorization check
        if (user.getRole() == Role.USER &&
                !task.getCreatedBy().getId().equals(user.getId())) {

            throw new UnauthorizedException("You are not allowed to delete this task");
        }

        taskRepo.delete(task);
    }


    private TaskResponse mapToResponse(Task task) {

        TaskResponse response = new TaskResponse();

        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());

        return response;
    }
}
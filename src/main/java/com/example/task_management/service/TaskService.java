package com.example.task_management.service;

import com.example.task_management.dto.Task.TaskRequest;
import com.example.task_management.dto.Task.TaskResponse;

import java.util.List;

public interface TaskService {
    TaskResponse createTask(TaskRequest taskRequest);
    List<TaskResponse> getTasks();
    TaskResponse updateTask(Long id, TaskRequest taskRequest);
    void deleteTask(Long id);
}

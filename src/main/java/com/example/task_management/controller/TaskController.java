package com.example.task_management.controller;

import com.example.task_management.dto.Task.TaskRequest;
import com.example.task_management.dto.Task.TaskResponse;
import com.example.task_management.entity.Task;
import com.example.task_management.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping()
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest){
        return ResponseEntity.ok(taskService.createTask(taskRequest));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>>getTasks(){

        return ResponseEntity.ok(taskService.getTasks());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest){
        return ResponseEntity.ok(taskService.updateTask(id,taskRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted Successfully");
    }
}

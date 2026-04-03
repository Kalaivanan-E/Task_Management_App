package com.example.task_management.controller;

import com.example.task_management.Service.AuthService;
import com.example.task_management.dto.Auth.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/create")
    public String registerUser(@RequestBody RegisterRequest registerRequest){
        return authService.registerUser(registerRequest);
    }
}

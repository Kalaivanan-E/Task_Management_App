package com.example.task_management.controller;

import com.example.task_management.dto.Auth.AuthResponse;
import com.example.task_management.dto.Auth.LoginRequest;
import com.example.task_management.service.AuthService;
import com.example.task_management.dto.Auth.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/create")
    public String registerUser(@RequestBody RegisterRequest registerRequest){
        return authService.registerUser(registerRequest);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse>loginUser(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.loginUser(loginRequest));
    }
}

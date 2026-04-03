package com.example.task_management.service;

import com.example.task_management.dto.Auth.AuthResponse;
import com.example.task_management.dto.Auth.LoginRequest;
import com.example.task_management.dto.Auth.RegisterRequest;

public interface AuthService {
    String registerUser(RegisterRequest registerRequest);
    AuthResponse loginUser(LoginRequest loginRequest);
}

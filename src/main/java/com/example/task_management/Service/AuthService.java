package com.example.task_management.Service;

import com.example.task_management.dto.Auth.RegisterRequest;

public interface AuthService {
    String registerUser(RegisterRequest registerRequest);
}

package com.example.task_management.service.impl;

import com.example.task_management.config.JwtUtil;
import com.example.task_management.dto.Auth.AuthResponse;
import com.example.task_management.dto.Auth.LoginRequest;
import com.example.task_management.dto.Auth.RegisterRequest;
import com.example.task_management.entity.Role;
import com.example.task_management.entity.User;
import com.example.task_management.exception.ResourceNotFoundException;
import com.example.task_management.exception.UnauthorizedException;
import com.example.task_management.repository.UserRepository;
import com.example.task_management.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String registerUser(RegisterRequest registerRequest) {


        userRepo.findByEmail(registerRequest.getEmail())
                .ifPresent(user -> {
                    throw new UnauthorizedException("Email already exists");
                });


        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());


        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodedPassword);


        user.setRole(Role.USER);

        userRepo.save(user);

        return "User registered successfully";
    }


    @Override
    public AuthResponse loginUser(LoginRequest loginRequest) {


        User user = userRepo.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));


        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }


        String accessToken = jwtUtil.generateAccessToken(user.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

        return new AuthResponse(accessToken, refreshToken);
    }
}
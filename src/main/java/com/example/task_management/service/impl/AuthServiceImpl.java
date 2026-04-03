package com.example.task_management.service.impl;

import com.example.task_management.config.JwtUtil;
import com.example.task_management.dto.Auth.AuthResponse;
import com.example.task_management.dto.Auth.LoginRequest;
import com.example.task_management.service.AuthService;
import com.example.task_management.dto.Auth.RegisterRequest;
import com.example.task_management.entity.User;
import com.example.task_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(RegisterRequest registerRequest){

        Optional<User>existUser = userRepo.findByEmail(registerRequest.getEmail());
        if(existUser.isPresent()){
            throw new RuntimeException("Email already Exist");
        }

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodedPassword);

        user.setRole(user.getRole());

        userRepo.save(user);

        return "Register Successfully";

    }

    @Override
    public AuthResponse loginUser(LoginRequest loginRequest) {
        User user = userRepo.findByEmail(loginRequest.getEmail())
                .orElseThrow(()->new RuntimeException("User not found"));

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        String accessToken = jwtUtil.generateAccessToken(user.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

        return new AuthResponse(accessToken,refreshToken);
    }
}

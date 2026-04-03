package com.example.task_management.dto.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "UserName is required")
    private String userName;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email format")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must atleast 6 character")
    private String password;
}

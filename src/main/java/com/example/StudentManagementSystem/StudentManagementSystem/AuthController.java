package com.example.StudentManagementSystem.StudentManagementSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService ser;

    @PostMapping("/register")
    public User register(
            @RequestBody User user) {

        return ser.register(user);
    }
    
    @PostMapping("/login")
    public String login(
            @RequestBody User user) {

        return ser.login(user);
    }
}
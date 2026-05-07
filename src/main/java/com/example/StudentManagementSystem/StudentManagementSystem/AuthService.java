package com.example.StudentManagementSystem.StudentManagementSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.StudentManagementSystem.StudentManagementSystem.security.JwtService;
@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    // Register User
    public User register(User user) {

        user.setPassword(
                encoder.encode(user.getPassword()));

        return repo.save(user);
    }
    
    @Autowired
    private JwtService jwtService;
    public String login(User user) {

        Optional<User> existingUser =
                repo.findByUsername(
                        user.getUsername());

        if(existingUser.isPresent()) {

            User dbUser =
                    existingUser.get();

            if(encoder.matches(
                    user.getPassword(),
                    dbUser.getPassword())) {

                return jwtService.generateToken(
                        user.getUsername());
            }
        }

        return "Invalid Username or Password";
    }
}
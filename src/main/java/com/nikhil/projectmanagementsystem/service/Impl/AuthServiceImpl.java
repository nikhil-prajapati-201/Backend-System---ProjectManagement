package com.nikhil.projectmanagementsystem.service.Impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nikhil.projectmanagementsystem.auth.dto.LoginRequest;
import com.nikhil.projectmanagementsystem.auth.dto.RegisterRequest;
import com.nikhil.projectmanagementsystem.entity.User;
import com.nikhil.projectmanagementsystem.repository.UserRepository;
import com.nikhil.projectmanagementsystem.security.JwtService;
import com.nikhil.projectmanagementsystem.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl
        implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    
    private final JwtService jwtService;

    @Override
    public String register(
            RegisterRequest request) {

    	User user = User.builder()
    	        .username(request.getUsername())
    	        .email(request.getEmail())
    	        .password(
    	            passwordEncoder.encode(
    	                request.getPassword()))
    	        .role(request.getRole())
    	        .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @Override
    public String login(LoginRequest request) {

        User user =
                userRepository.findByEmail(
                        request.getEmail())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User Not Found"));

        boolean isPasswordMatch =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword());

        if (!isPasswordMatch) {
            throw new RuntimeException(
                    "Invalid Password");
        }

        return jwtService.generateToken(
                user.getEmail());
    }
}
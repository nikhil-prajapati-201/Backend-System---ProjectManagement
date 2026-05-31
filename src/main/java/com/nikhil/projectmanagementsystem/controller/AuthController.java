package com.nikhil.projectmanagementsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.projectmanagementsystem.auth.dto.LoginRequest;
import com.nikhil.projectmanagementsystem.auth.dto.RegisterRequest;
import com.nikhil.projectmanagementsystem.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String>
    register(
            @RequestBody RegisterRequest request){

        return ResponseEntity.ok(
                authService.register(request));
    }
    
    @PostMapping("/login")
    public ResponseEntity<String>
    login(
            @RequestBody LoginRequest request){

        return ResponseEntity.ok(
                authService.login(request));
    }
}

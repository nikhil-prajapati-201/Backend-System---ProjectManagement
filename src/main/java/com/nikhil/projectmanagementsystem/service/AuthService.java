package com.nikhil.projectmanagementsystem.service;

import com.nikhil.projectmanagementsystem.auth.dto.LoginRequest;
import com.nikhil.projectmanagementsystem.auth.dto.RegisterRequest;


public interface AuthService {

	 String register(RegisterRequest request);
	 
	 String login(LoginRequest request);
}

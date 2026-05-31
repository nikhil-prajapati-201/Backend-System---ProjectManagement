package com.nikhil.projectmanagementsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TeamMemberRequestDTO {

	@NotBlank(message = "Name is required")
	private String name;
	
	@Email(message = "Invalid email")
	private String email;
	
	@NotBlank(message = "Role is required")
	private String role;
	
	@NotBlank(message = "Department is required")
	private String department;
	
}

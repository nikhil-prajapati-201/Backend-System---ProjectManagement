package com.nikhil.projectmanagementsystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamMemberResponseDTO {

	private Long id;
	private String name;
	private String email;
	private String role;
	private String department;
}

package com.nikhil.projectmanagementsystem.service;

import java.util.List;

import com.nikhil.projectmanagementsystem.dto.TeamMemberResponseDTO;
import com.nikhil.projectmanagementsystem.dto.TeamMemberRequestDTO;

public interface TeamMemberService {

	//	Add Team Member
	//	Update Team Member
	//	Delete Team Member
	//	Get All Members
	
	TeamMemberResponseDTO addTeamMeber(TeamMemberRequestDTO teamMember);
	
	TeamMemberResponseDTO updateTeamMember(Long id, TeamMemberRequestDTO teamMember);
	
	List<TeamMemberResponseDTO> getAllTeamMember();
	
	TeamMemberResponseDTO getTeamMember(Long id);
	
	void deleteTeamMember(Long id);
	
}

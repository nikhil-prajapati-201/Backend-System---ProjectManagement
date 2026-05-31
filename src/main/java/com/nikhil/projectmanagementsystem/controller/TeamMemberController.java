package com.nikhil.projectmanagementsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.projectmanagementsystem.dto.TeamMemberRequestDTO;
import com.nikhil.projectmanagementsystem.dto.TeamMemberResponseDTO;
import com.nikhil.projectmanagementsystem.service.Impl.TeamMemberServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class TeamMemberController {

	private final TeamMemberServiceImpl serviceImpl;

	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<TeamMemberResponseDTO> addMember(@Valid @RequestBody TeamMemberRequestDTO dto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(serviceImpl.addTeamMeber(dto));
	}

	@GetMapping("/getAll")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	public ResponseEntity<List<TeamMemberResponseDTO>>
	getAllMembers(){

	    return ResponseEntity.ok(
	    		serviceImpl.getAllTeamMember());
	}
	
	@GetMapping("/get/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	public ResponseEntity<TeamMemberResponseDTO>
	getMemberById(@PathVariable Long id){

	    return ResponseEntity.ok(
	    		serviceImpl.getTeamMember(id));
	}
	
	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<TeamMemberResponseDTO>
	updateMember(
	        @PathVariable Long id,
	        @Valid
	        @RequestBody TeamMemberRequestDTO dto){

	    return ResponseEntity.ok(
	    		serviceImpl.updateTeamMember(id,dto));
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String>
	deleteMember(@PathVariable Long id){

		serviceImpl.deleteTeamMember(id);

	    return ResponseEntity.ok(
	            "Member Deleted Successfully");
	}
	
}

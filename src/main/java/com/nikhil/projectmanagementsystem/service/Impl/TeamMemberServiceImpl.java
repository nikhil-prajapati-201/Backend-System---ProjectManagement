package com.nikhil.projectmanagementsystem.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nikhil.projectmanagementsystem.dto.TeamMemberResponseDTO;
import com.nikhil.projectmanagementsystem.dto.TeamMemberRequestDTO;
import com.nikhil.projectmanagementsystem.entity.TeamMember;
import com.nikhil.projectmanagementsystem.exception.ResourceNotFoundException;
import com.nikhil.projectmanagementsystem.repository.TeamMemberRepository;
import com.nikhil.projectmanagementsystem.service.TeamMemberService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {

	private final TeamMemberRepository teamMemberRepository;
	private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

	@Override
	public TeamMemberResponseDTO addTeamMeber(TeamMemberRequestDTO teamMember) {

		TeamMember member = TeamMember.builder().name(teamMember.getName()).department(teamMember.getDepartment())
				.email(teamMember.getEmail()).role(teamMember.getRole()).build();

		logger.info("Creating Member: {}", member.getName());
		TeamMember saveMember = teamMemberRepository.save(member);
		logger.info("Member created succesfully: {}", member.getName());

		return mapToResponse(saveMember);
	}

	@Override
	public TeamMemberResponseDTO updateTeamMember(Long id, TeamMemberRequestDTO teamMember) {

		logger.info("Updating Member with ID: {}", id);
		TeamMember member = teamMemberRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Team Member Not found: " + id));

		member.setName(teamMember.getName());
		member.setDepartment(teamMember.getDepartment());
		member.setEmail(teamMember.getEmail());
		member.setRole(teamMember.getRole());

		teamMemberRepository.save(member);
		logger.info("Member Updated with Id: {}", id);
		return mapToResponse(member);
	}

	@Override
	public List<TeamMemberResponseDTO> getAllTeamMember() {

		List<TeamMember> listOfTeamMembers = teamMemberRepository.findAll();
		List<TeamMemberResponseDTO> listOfResponse = listOfTeamMembers.stream().map(this::mapToResponse).toList();
		return listOfResponse;
	}

	@Override
	public TeamMemberResponseDTO getTeamMember(Long id) {

		logger.info("Fetching Member with ID: {}", id);
		TeamMember teamMember = teamMemberRepository.findById(id).orElseThrow(() -> {
			logger.error("Fetching Member with ID: {}", id);
			return new ResourceNotFoundException("Member Not Found with ID: " + id);
		});
		return mapToResponse(teamMember);
	}

	@Override
	public void deleteTeamMember(Long id) {
		logger.info(
		        "Deleting Task with ID: {}",
		        id);
		teamMemberRepository.deleteById(id);
		logger.info(
		        "Member deleted with ID: {}",
		        id);
	}

	private TeamMemberResponseDTO mapToResponse(TeamMember member) {

		return TeamMemberResponseDTO.builder().id(member.getId()).name(member.getName()).email(member.getEmail())
				.role(member.getRole()).department(member.getDepartment()).build();
	}

}

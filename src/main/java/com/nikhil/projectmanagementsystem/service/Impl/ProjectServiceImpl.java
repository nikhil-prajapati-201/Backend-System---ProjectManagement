package com.nikhil.projectmanagementsystem.service.Impl;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nikhil.projectmanagementsystem.dto.ProjectRequestDTO;
import com.nikhil.projectmanagementsystem.dto.ProjectResponseDTO;
import com.nikhil.projectmanagementsystem.entity.Project;
import com.nikhil.projectmanagementsystem.exception.ResourceNotFoundException;
import com.nikhil.projectmanagementsystem.repository.ProjectRepository;
import com.nikhil.projectmanagementsystem.service.ProjectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;
	private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

	@Override
	public ProjectResponseDTO createProject(ProjectRequestDTO requestDTO) {
		Project project = Project.builder().projectName(requestDTO.getProjectName())
				.description(requestDTO.getDescription()).startDate(requestDTO.getStartDate())
				.endDate(requestDTO.getEndDate()).status(requestDTO.getStatus()).build();

		logger.info("Creating project: {}", requestDTO.getProjectName());
		Project savedProject = projectRepository.save(project);
		logger.info("Project created successfully with ID: {}", savedProject.getId());

		return mapToResponse(savedProject);
	}

	private ProjectResponseDTO mapToResponse(Project project) {

		return ProjectResponseDTO.builder().id(project.getId()).projectName(project.getProjectName())
				.description(project.getDescription()).startDate(project.getStartDate()).endDate(project.getEndDate())
				.status(project.getStatus()).build();
	}

	@Override
	public List<ProjectResponseDTO> getAllProjects() {
		List<ProjectResponseDTO> projectResponseList = new ArrayList();

		List<Project> ListOfProjects = projectRepository.findAll();

		projectResponseList = ListOfProjects.stream().map(this::mapToResponse).toList();
		return projectResponseList;
	}

	@Override
	public ProjectResponseDTO getProjectById(Long id) {
		logger.info("Fetching project with ID: {}", id);
		Project project = projectRepository.findById(id).orElseThrow(() -> {

			logger.error("Project not found with ID: {}", id);

			return new ResourceNotFoundException("Project Not Found With ID : " + id);
		});

		return mapToResponse(project);
	}

	@Override
	public ProjectResponseDTO updateProject(Long id, ProjectRequestDTO requestDTO) {

		logger.info(
		        "Updating project with ID: {}",
		        id);
		
		Project project = projectRepository.findById(id).orElseThrow();

		project.setProjectName(requestDTO.getProjectName());

		project.setDescription(requestDTO.getDescription());

		project.setStartDate(requestDTO.getStartDate());

		project.setEndDate(requestDTO.getEndDate());

		project.setStatus(requestDTO.getStatus());

		Project updatedProject = projectRepository.save(project);
		logger.info(
		        "Project Updated with Id: {}",
		        id);
		return mapToResponse(updatedProject);
	}

	@Override
	public void deleteProject(Long id) {
		logger.info(
		        "Deleting project with ID: {}",
		        id);
		
		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Project Not Found With ID : " + id));
		projectRepository.deleteById(id);

		logger.info(
		        "Project deleted with ID: {}",
		        id);
	}

}
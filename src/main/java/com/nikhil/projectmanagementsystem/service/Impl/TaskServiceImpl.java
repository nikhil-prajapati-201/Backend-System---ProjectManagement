package com.nikhil.projectmanagementsystem.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nikhil.projectmanagementsystem.dto.TaskRequestDTO;
import com.nikhil.projectmanagementsystem.dto.TaskResponseDTO;
import com.nikhil.projectmanagementsystem.entity.Project;
import com.nikhil.projectmanagementsystem.entity.Task;
import com.nikhil.projectmanagementsystem.entity.TeamMember;
import com.nikhil.projectmanagementsystem.exception.ResourceNotFoundException;
import com.nikhil.projectmanagementsystem.repository.ProjectRepository;
import com.nikhil.projectmanagementsystem.repository.TaskRepository;
import com.nikhil.projectmanagementsystem.repository.TeamMemberRepository;
import com.nikhil.projectmanagementsystem.service.TaskService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	private final ProjectRepository projectRepository;
	private final TeamMemberRepository teamMemberRepository;
	private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
	@Override
	public TaskResponseDTO createTask(TaskRequestDTO dto) {

		Project project = projectRepository.findById(dto.getProjectId())
				.orElseThrow(() -> new ResourceNotFoundException("Project Not Found"));

		TeamMember member = teamMemberRepository.findById(dto.getMemberId())
				.orElseThrow(() -> new ResourceNotFoundException("Member Not Found"));

		Task task = Task.builder().taskTitle(dto.getTaskTitle()).description(dto.getDescription())
				.priority(dto.getPriority()).status(dto.getStatus()).dueDate(dto.getDueDate()).project(project)
				.teamMember(member).build();
		logger.info("Creating Task: {}", task.getTaskTitle());
		Task saveTask = taskRepository.save(task);
		logger.info("Task created successfully: {}", task.getTaskTitle());
		return mapToResponse(saveTask);
	}

	@Override
	public List<TaskResponseDTO> getAllTasks() {
		return taskRepository.findAll().stream().map(this::mapToResponse).toList();
	}

	@Override
	public TaskResponseDTO getTaskById(Long id) {
		logger.info("Fetching task with ID: {}", id);
		Task task = taskRepository.findById(id).orElseThrow(() -> {

			logger.error("Task not found with ID: {}", id);

			return new ResourceNotFoundException("Task Not Found With ID : " + id);
		});

		return mapToResponse(task);
	}

	@Override
	public TaskResponseDTO updateTask(
	        Long id,
	        TaskRequestDTO dto) {

		logger.info(
		        "Updating Task with ID: {}",
		        id);  
		
	    Task task = taskRepository.findById(id)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Task Not Found With ID : " + id));

	    Project project = projectRepository
	            .findById(dto.getProjectId())
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Project Not Found"));

	    TeamMember member = teamMemberRepository
	            .findById(dto.getMemberId())
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Member Not Found"));

	    task.setTaskTitle(dto.getTaskTitle());
	    task.setDescription(dto.getDescription());
	    task.setPriority(dto.getPriority());
	    task.setStatus(dto.getStatus());
	    task.setDueDate(dto.getDueDate());

	    task.setProject(project);
	    task.setTeamMember(member);

	    Task savedTask = taskRepository.save(task);
	    logger.info(
		        "Task Updated with Id: {}",
		        id);
	    return mapToResponse(savedTask);
	}

	@Override
	public void deleteTask(Long id) {
		logger.info(
		        "Deleting Task with ID: {}",
		        id);
		Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task Not Found"));

		taskRepository.delete(task);
		logger.info(
		        "Task deleted with ID: {}",
		        id);

	}

	public TaskResponseDTO mapToResponse(Task task) {
		return TaskResponseDTO.builder().id(task.getId()).taskTitle(task.getTaskTitle())
				.description(task.getDescription()).priority(task.getPriority()).status(task.getStatus())
				.dueDate(task.getDueDate()).projectId(task.getProject().getId())
				.projectName(task.getProject().getProjectName()).memberId(task.getTeamMember().getId())
				.memberName(task.getTeamMember().getName()).build();
	}

}

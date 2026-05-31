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

import com.nikhil.projectmanagementsystem.dto.TaskRequestDTO;
import com.nikhil.projectmanagementsystem.dto.TaskResponseDTO;
import com.nikhil.projectmanagementsystem.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

	private final TaskService taskService;

	@PostMapping("/add")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO dto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(dto));
	}

	@GetMapping("/getAll")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','MEMBER')")
	public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {

		return ResponseEntity.ok(taskService.getAllTasks());
	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','MEMBER')")
	public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {

		return ResponseEntity.ok(taskService.getTaskById(id));
	}
	
	@PutMapping("/update/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	public ResponseEntity<TaskResponseDTO>
	updateTask(
	        @PathVariable Long id,
	        @Valid
	        @RequestBody TaskRequestDTO dto){

	    return ResponseEntity.ok(
	            taskService.updateTask(id,dto));
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String>
	deleteTask(
	        @PathVariable Long id){

	    taskService.deleteTask(id);

	    return ResponseEntity.ok(
	            "Task Deleted Successfully");
	}
}
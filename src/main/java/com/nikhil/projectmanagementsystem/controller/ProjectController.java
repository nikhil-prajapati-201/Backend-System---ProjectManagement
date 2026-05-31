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

import com.nikhil.projectmanagementsystem.dto.ProjectRequestDTO;
import com.nikhil.projectmanagementsystem.dto.ProjectResponseDTO;
import com.nikhil.projectmanagementsystem.service.ProjectService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

	
    private final ProjectService projectService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")	
    public ResponseEntity<ProjectResponseDTO> createProject(
            @Valid @RequestBody ProjectRequestDTO requestDTO){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(projectService.createProject(requestDTO));
    }
    
    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects(){
    	
    	return ResponseEntity.ok(projectService.getAllProjects());
    }
    
    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable Long id){
    	return ResponseEntity.ok(projectService.getProjectById(id));
    }
    
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<ProjectResponseDTO> updateProject(@PathVariable Long id,
    													@Valid @RequestBody ProjectRequestDTO projectRequest){
    	return ResponseEntity.ok(projectService.updateProject(id, projectRequest));
    }
    
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String>
    deleteProject(@PathVariable Long id){

        projectService.deleteProject(id);

        return ResponseEntity.ok("Project Deleted Successfully");
    }
    
    
}
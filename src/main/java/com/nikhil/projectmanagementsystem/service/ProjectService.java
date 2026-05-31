package com.nikhil.projectmanagementsystem.service;

import java.util.List;

import com.nikhil.projectmanagementsystem.dto.ProjectRequestDTO;
import com.nikhil.projectmanagementsystem.dto.ProjectResponseDTO;

public interface ProjectService {


    ProjectResponseDTO createProject(ProjectRequestDTO requestDTO);

    List<ProjectResponseDTO> getAllProjects();

    ProjectResponseDTO getProjectById(Long id);

    ProjectResponseDTO updateProject(Long id, ProjectRequestDTO requestDTO);

    void deleteProject(Long id);
    
}

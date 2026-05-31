package com.nikhil.projectmanagementsystem.service;

import java.util.List;

import com.nikhil.projectmanagementsystem.dto.TaskRequestDTO;
import com.nikhil.projectmanagementsystem.dto.TaskResponseDTO;

public interface TaskService {

	TaskResponseDTO createTask(TaskRequestDTO dto);

    List<TaskResponseDTO> getAllTasks();

    TaskResponseDTO getTaskById(Long id);

    TaskResponseDTO updateTask(
            Long id,
            TaskRequestDTO dto);

    void deleteTask(Long id);
}

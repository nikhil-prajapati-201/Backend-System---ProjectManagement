package com.nikhil.projectmanagementsystem.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResponseDTO {

	private Long id;

    private String taskTitle;

    private String description;

    private String priority;

    private String status;

    private LocalDate dueDate;

    private Long projectId;

    private String projectName;

    private Long memberId;

    private String memberName;
}

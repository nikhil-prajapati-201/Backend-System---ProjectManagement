package com.nikhil.projectmanagementsystem.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskRequestDTO {

	 @NotBlank(message = "Task title is required")
	    private String taskTitle;

	    @NotBlank(message = "Description is required")
	    private String description;

	    @NotBlank(message = "Priority is required")
	    private String priority;

	    @NotBlank(message = "Status is required")
	    private String status;

	    @NotNull(message = "Due date is required")
	    private LocalDate dueDate;

	    @NotNull(message = "Project ID is required")
	    private Long projectId;

	    @NotNull(message = "Member ID is required")
	    private Long memberId;
}

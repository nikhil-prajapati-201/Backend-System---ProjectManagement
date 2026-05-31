package com.nikhil.projectmanagementsystem.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectRequestDTO {

	 @NotBlank(message = "Project name is required")
	    private String projectName;

	    @NotBlank(message = "Description is required")
	    private String description;

	    @NotNull(message = "Start date is required")
	    private LocalDate startDate;

	    @NotNull(message = "End date is required")
	    private LocalDate endDate;

	    @NotBlank(message = "Status is required")
	    private String status;
}

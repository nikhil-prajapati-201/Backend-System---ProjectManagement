package com.nikhil.projectmanagementsystem.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectResponseDTO {

   

	private Long id;

    private String projectName;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;
}

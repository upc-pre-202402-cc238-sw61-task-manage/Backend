package com.taskmanager.backend.project.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public class UpdateProjectCommand {
    @NotBlank
    private Long projectId;
    @NotBlank
    private String projectName;
    @NotBlank
    private String projectDescription;
    @NotBlank
    private String startDate;
    @NotBlank
    private String endDate;
    @NotBlank
    private String projectManager;

    // Getters
    public Long getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getProjectManager() {
        return projectManager;
    }

    // Constructor
    public UpdateProjectCommand(Long projectId, String projectName, String projectDescription, String startDate, String endDate, String projectManager) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectManager = projectManager;
    }
}

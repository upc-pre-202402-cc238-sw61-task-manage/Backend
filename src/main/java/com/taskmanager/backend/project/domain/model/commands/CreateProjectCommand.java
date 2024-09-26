package com.taskmanager.backend.project.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public class CreateProjectCommand {
    @NotBlank
    private String projectName;
    @NotBlank
    private String projectDescription;
    @NotBlank
    private String projectManager;
    @NotBlank
    private String projectMember;

    // Getters
    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public String getProjectMember() {
        return projectMember;
    }

    // Constructor
    public CreateProjectCommand(String projectName, String projectDescription, String projectManager, String projectMember) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectManager = projectManager;
        this.projectMember = projectMember;
    }
}
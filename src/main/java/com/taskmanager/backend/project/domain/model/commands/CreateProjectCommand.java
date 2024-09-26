package com.taskmanager.backend.project.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record CreateProjectCommand(
        @NotBlank String projectName,
        @NotBlank String projectDescription,
        @NotBlank String projectManager,
        @NotBlank String projectMember
) {
}

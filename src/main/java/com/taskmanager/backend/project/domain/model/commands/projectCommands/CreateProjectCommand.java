package com.taskmanager.backend.project.domain.model.commands.projectCommands;

import jakarta.validation.constraints.NotBlank;

public record CreateProjectCommand(
        @NotBlank Long teamId,
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank String leader
) {
}

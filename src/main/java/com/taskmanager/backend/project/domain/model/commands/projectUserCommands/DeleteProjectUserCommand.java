package com.taskmanager.backend.project.domain.model.commands.projectUserCommands;

import jakarta.validation.constraints.NotBlank;

public record DeleteProjectUserCommand(
        @NotBlank Long projectId,
        @NotBlank Long userId
) {
}

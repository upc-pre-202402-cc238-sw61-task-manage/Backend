package com.taskmanager.backend.projectUsers.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record CreateProjectUserCommand(
        @NotBlank Long projectId,
        @NotBlank Long userId
) {
}

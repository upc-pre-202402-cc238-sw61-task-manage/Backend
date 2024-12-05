package com.taskmanager.backend.project.domain.model.commands.projectusercommands;

import jakarta.validation.constraints.NotBlank;

public record DeleteProjectUserCommand(
        @NotBlank Long projectId,
        @NotBlank Long userId
) {
}

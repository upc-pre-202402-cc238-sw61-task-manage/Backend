package com.taskmanager.backend.project.domain.model.commands.taskCommands;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record CreateTaskCommand(
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank LocalDateTime dueDate,
        @NotBlank Long projectId,
        @NotBlank Long userId
) {
}

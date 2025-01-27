package com.taskmanager.backend.project.domain.model.commands.taskCommands;

import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatusList;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record UpdateTaskCommand(
        @NotBlank Long taskId,
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank LocalDateTime dueDate,
        @NotBlank Long userId,
        @NotBlank TaskStatusList status
) {
}

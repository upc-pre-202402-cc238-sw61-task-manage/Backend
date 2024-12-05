package com.taskmanager.backend.project.domain.model.commands.taskcommands;

import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatus;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UpdateTaskCommand(
        @NotBlank Long taskId,
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank LocalDate dueDate,
        @NotBlank Long userId,
        @NotBlank TaskStatus status
) {
}

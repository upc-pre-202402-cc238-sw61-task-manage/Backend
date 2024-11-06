package com.taskmanager.backend.tasks.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreateTaskCommand(
        @NotBlank String taskName,
        @NotBlank String taskDescription,
        @NotBlank LocalDate dueDate,
        @NotBlank Long projectId,
        @NotBlank Long userId
) {
}

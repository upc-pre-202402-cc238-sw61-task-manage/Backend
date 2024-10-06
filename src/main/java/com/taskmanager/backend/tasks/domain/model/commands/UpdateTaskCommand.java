package com.taskmanager.backend.tasks.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UpdateTaskCommand(
        @NotBlank Long id,
        @NotBlank String taskName,
        @NotBlank String taskDescription,
        @NotBlank LocalDate dueDate,
        @NotBlank Long assignUser
) {
}

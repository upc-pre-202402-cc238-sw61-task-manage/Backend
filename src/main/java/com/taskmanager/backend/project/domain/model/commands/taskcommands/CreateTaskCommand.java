package com.taskmanager.backend.project.domain.model.commands.taskcommands;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreateTaskCommand(
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank LocalDate dueDate,
        @NotBlank Long projectId,
        @NotBlank Long userId
) {
}

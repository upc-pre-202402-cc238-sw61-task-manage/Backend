package com.taskmanager.backend.tasks.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record CreateTaskCommand(
        @NotBlank String taskName,
        @NotBlank String taskDescription,
        @NotBlank Date dueDate,
        @NotBlank Long projectUUID,
        @NotBlank int assignUser
) {
}

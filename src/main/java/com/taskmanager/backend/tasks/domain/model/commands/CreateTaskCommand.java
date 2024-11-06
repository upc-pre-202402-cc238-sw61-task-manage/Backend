package com.taskmanager.backend.tasks.domain.model.commands;

import com.taskmanager.backend.tasks.domain.model.valueObjects.TaskStatus;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreateTaskCommand(
        @NotBlank String taskName,
        @NotBlank String taskDescription,
        @NotBlank LocalDate dueDate,
        @NotBlank Long projectUUID,
        @NotBlank Long assignUser
) {
}

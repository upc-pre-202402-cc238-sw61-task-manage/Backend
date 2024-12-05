package com.taskmanager.backend.project.domain.model.commands.taskcommands;

import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatus;
import jakarta.validation.constraints.NotBlank;

public record PatchTaskStatusCommand(
        @NotBlank Long taskId,
        @NotBlank TaskStatus status
) {
}

package com.taskmanager.backend.project.domain.model.commands.taskCommands;

import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatusList;
import jakarta.validation.constraints.NotBlank;

public record PatchTaskStatusCommand(
        @NotBlank Long taskId,
        @NotBlank TaskStatusList status
) {
}

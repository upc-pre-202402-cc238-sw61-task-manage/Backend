package com.taskmanager.backend.project.domain.model.commands.taskCommands;

import jakarta.validation.constraints.NotBlank;

public record DeleteTaskCommand(@NotBlank Long taskId) {
}

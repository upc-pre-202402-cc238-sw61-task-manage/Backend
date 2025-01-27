package com.taskmanager.backend.project.domain.model.commands.projectCommands;

import jakarta.validation.constraints.NotNull;

public record DeleteProjectCommand(@NotNull Long projectId) {
}

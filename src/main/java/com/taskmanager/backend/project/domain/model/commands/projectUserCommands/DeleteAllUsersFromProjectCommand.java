package com.taskmanager.backend.project.domain.model.commands.projectUserCommands;

public record DeleteAllUsersFromProjectCommand(
        Long projectId
) {
}

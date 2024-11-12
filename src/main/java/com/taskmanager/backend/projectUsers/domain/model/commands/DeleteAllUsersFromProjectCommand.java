package com.taskmanager.backend.projectUsers.domain.model.commands;

public record DeleteAllUsersFromProjectCommand(
        Long projectId
) {
}

package com.taskmanager.backend.project.domain.model.commands.projectusercommands;

public record DeleteAllUsersFromProjectCommand(
        Long projectId
) {
}

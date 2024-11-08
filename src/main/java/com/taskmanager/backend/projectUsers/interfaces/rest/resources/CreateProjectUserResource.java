package com.taskmanager.backend.projectUsers.interfaces.rest.resources;

public record CreateProjectUserResource(
        Long projectId,
        Long userId
) {
}

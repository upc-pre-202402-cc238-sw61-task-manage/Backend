package com.taskmanager.backend.projectUsers.interfaces.rest.resources;

public record DeleteProjectUserResource(
        Long projectId,
        Long userId
) {
}

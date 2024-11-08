package com.taskmanager.backend.projectUsers.interfaces.rest.resources;

public record ProjectUserResource(
        Long projectId,
        Long userId
) {
}

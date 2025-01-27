package com.taskmanager.backend.project.interfaces.rest.resources.projectUserResources;

public record CreateProjectUserResource(
        Long projectId,
        Long userId
) {
}

package com.taskmanager.backend.project.interfaces.rest.resources.projectUserResources;

public record DeleteProjectUserResource(
        Long projectId,
        Long userId
) {
}

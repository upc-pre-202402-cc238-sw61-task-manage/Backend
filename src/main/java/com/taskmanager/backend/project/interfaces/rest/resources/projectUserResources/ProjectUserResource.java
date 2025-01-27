package com.taskmanager.backend.project.interfaces.rest.resources.projectUserResources;

public record ProjectUserResource(
        Long projectId,
        Long userId
) {
}

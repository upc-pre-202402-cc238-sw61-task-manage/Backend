package com.taskmanager.backend.project.interfaces.rest.resources.projectuserresources;

public record CreateProjectUserResource(
        Long projectId,
        Long userId
) {
}

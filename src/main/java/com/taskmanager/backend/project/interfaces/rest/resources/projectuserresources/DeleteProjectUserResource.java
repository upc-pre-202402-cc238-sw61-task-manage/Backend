package com.taskmanager.backend.project.interfaces.rest.resources.projectuserresources;

public record DeleteProjectUserResource(
        Long projectId,
        Long userId
) {
}

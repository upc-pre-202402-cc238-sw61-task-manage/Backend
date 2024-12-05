package com.taskmanager.backend.project.interfaces.rest.resources.projectuserresources;

public record ProjectUserResource(
        Long projectId,
        Long userId
) {
}

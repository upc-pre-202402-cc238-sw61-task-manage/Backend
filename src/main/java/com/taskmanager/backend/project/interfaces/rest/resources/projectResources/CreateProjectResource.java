package com.taskmanager.backend.project.interfaces.rest.resources.projectResources;

public record CreateProjectResource(
        Long groupId,
        String title,
        String description,
        String leader
) {
}
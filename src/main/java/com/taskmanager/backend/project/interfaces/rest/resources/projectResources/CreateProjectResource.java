package com.taskmanager.backend.project.interfaces.rest.resources.projectResources;

public record CreateProjectResource(
        Long teamId,
        String title,
        String description,
        String leader
) {
}
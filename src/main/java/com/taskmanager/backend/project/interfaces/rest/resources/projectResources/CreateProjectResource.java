package com.taskmanager.backend.project.interfaces.rest.resources.projectResources;

public record CreateProjectResource(
        String title,
        String description,
        String leader
) {
}
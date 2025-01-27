package com.taskmanager.backend.project.interfaces.rest.resources.projectResources;

public record UpdateProjectResource(
        String title,
        String description
) {
}
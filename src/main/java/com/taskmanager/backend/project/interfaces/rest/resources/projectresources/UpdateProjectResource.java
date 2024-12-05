package com.taskmanager.backend.project.interfaces.rest.resources.projectresources;

public record UpdateProjectResource(
        String title,
        String description
) {
}
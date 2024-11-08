package com.taskmanager.backend.project.interfaces.rest.resources;

public record UpdateProjectResource(
        String projectName,
        String projectDescription
) {
}
package com.taskmanager.backend.project.interfaces.rest.resources.projectresources;

public record CreateProjectResource(
        String title,
        String description,
        String leader
) {
}
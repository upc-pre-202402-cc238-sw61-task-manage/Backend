package com.taskmanager.backend.project.interfaces.rest.resources;

public record CreateProjectResource(
        String projectName,
        String projectDescription,
        String projectLeader
) {
}
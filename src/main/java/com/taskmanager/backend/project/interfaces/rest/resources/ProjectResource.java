package com.taskmanager.backend.project.interfaces.rest.resources;

public record ProjectResource(
        Long id,
        String projectName,
        String projectDescription,
        String projectLeader
) {
}
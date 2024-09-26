package com.taskmanager.backend.project.domain.model.queries;

public record GetProjectByNameQuery(String name) {
    public GetProjectByNameQuery {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Project name cannot be empty");
        }
    }
}

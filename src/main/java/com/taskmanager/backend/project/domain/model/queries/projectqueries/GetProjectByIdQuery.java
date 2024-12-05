package com.taskmanager.backend.project.domain.model.queries.projectqueries;

public record GetProjectByIdQuery(Long id) {
    public GetProjectByIdQuery {
        if (id == null){
            throw new IllegalArgumentException("taskId cannot be null");
        }
        if (id < 0){
            throw new IllegalArgumentException("taskId cannot be negative");
        }
    }
}

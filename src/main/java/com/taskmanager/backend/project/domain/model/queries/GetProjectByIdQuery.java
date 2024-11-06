package com.taskmanager.backend.project.domain.model.queries;

public record GetProjectByIdQuery(Long id) {
    public GetProjectByIdQuery {
        if (id == null){
            throw new IllegalArgumentException("id cannot be null");
        }
        if (id < 0){
            throw new IllegalArgumentException("id cannot be negative");
        }
    }
}
